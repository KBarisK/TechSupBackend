package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.Admin;
import com.backend.techsupbackend.model.AuthenticationResponse;
import com.backend.techsupbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Admin admin){
        return ResponseEntity.ok(authService.register(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Admin admin){
        return ResponseEntity.ok(authService.authenticate(admin));
    }
}
