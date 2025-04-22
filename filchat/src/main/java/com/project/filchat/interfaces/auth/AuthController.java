package com.project.filchat.interfaces.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.filchat.application.auth.AuthService;
import com.project.filchat.domain.auth.OAuthProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/{provider}")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto.LoginResponse> login(
        @RequestBody AuthDto.LoginRequest request,
        @PathVariable OAuthProvider provider
    ) {
        AuthDto.LoginResponse response = authService.login(request, provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
