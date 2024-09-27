package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.AuthLoginRequest;
import com.camayopolis.presentation.dto.AuthRegisterRequest;
import com.camayopolis.presentation.dto.AuthResponse;
import com.camayopolis.service.implementation.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailServiceImpl userDetailService;

    public AuthController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest userRequest) {
        AuthResponse response = userDetailService.registerUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        AuthResponse response = userDetailService.loginUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
