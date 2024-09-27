package com.camayopolis.config.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtTokenValidator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);

            try {
                DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);

                String username = jwtUtil.extractUsername(decodedJWT);
                String authority = jwtUtil.getSpecificClaim(decodedJWT, "authorities").asString();

                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(grantedAuthority));

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

            } catch (JWTVerificationException ex) {
                throw new JWTVerificationException("Token invalid - Not authorized");
            }
        }

        filterChain.doFilter(request, response);
    }
}
