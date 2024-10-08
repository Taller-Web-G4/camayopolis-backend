package com.camayopolis.presentation.controller;

import com.camayopolis.persistence.entity.UserEntity;
import com.camayopolis.presentation.dto.AuthLoginRequest;
import com.camayopolis.presentation.dto.AuthRegisterRequest;
import com.camayopolis.presentation.dto.AuthResponse;
import com.camayopolis.service.implementation.UserDetailServiceImpl;
import com.camayopolis.service.interfaces.IConfirmationTokenService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final IConfirmationTokenService confirmationTokenService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest userRequest) {
        AuthResponse response = userDetailService.registerUser(userRequest);

        UserEntity userEntity = userDetailService.findByEmail(userRequest.email());

        try {
            confirmationTokenService.sendConfirmationToken(userEntity);
        } catch (MessagingException e) {
            // TODO: cambiar con un log de verdad
            System.err.println("Error al enviar el correo de confirmación: " + e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        AuthResponse response = userDetailService.loginUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token) {
        confirmationTokenService.confirmUserToken(token);
        return ResponseEntity.ok("Tu cuenta ha sido confirmada con éxito");
    }

    @PostMapping("/resend")
    public ResponseEntity<String> resendConfirmationEmail(@RequestParam("email") String email) throws MessagingException {
        UserEntity userEntity = userDetailService.findByEmail(email);
        if (userEntity.getUsuActivado()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta ya ha sido activada.");
        }
        confirmationTokenService.sendConfirmationToken(userEntity);
        return ResponseEntity.ok("Correo de confirmación reenviado");
    }
}
