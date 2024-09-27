package com.camayopolis.service.implementation;

import com.camayopolis.config.security.JwtUtil;
import com.camayopolis.persistence.entity.RoleEnum;
import com.camayopolis.persistence.entity.UserEntity;
import com.camayopolis.persistence.repository.UserRepository;
import com.camayopolis.presentation.dto.AuthLoginRequest;
import com.camayopolis.presentation.dto.AuthRegisterRequest;
import com.camayopolis.presentation.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsuCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_".concat(userEntity.getUsuRol().name()));

        return new User(userEntity.getUsuCorreo(),
                userEntity.getUsuContrasena(),
                Collections.singleton(authority));
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtil.createToken(authentication);
        return new AuthResponse(email, "Login exitoso", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña inválida");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse registerUser(AuthRegisterRequest authRegisterRequest) {

        if (userRepository.existsByUsuCorreo(authRegisterRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo electrónico ya está registrado.");
        }

        UserEntity userEntity = UserEntity.builder()
                .usuCorreo(authRegisterRequest.email())
                .usuContrasena(passwordEncoder.encode(authRegisterRequest.password()))
                .usuRol(RoleEnum.USER)
                .build();

        userRepository.save(userEntity);

        String accessToken = jwtUtil.createToken(new UsernamePasswordAuthenticationToken(userEntity.getUsuCorreo(), userEntity.getUsuContrasena(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

        return new AuthResponse(userEntity.getUsuCorreo(), "Usuario registrado exitosamente", accessToken, true);
    }
}
