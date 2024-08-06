package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Admin;
import com.backend.techsupbackend.model.AuthenticationResponse;
import com.backend.techsupbackend.repository.AdminRepository;
import com.backend.techsupbackend.security.JwtService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Admin request){
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin = adminRepository.save(admin);

        String token = jwtService.generateToken(admin);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(Admin request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        Admin admin = adminRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(admin);

        return new AuthenticationResponse(token);
    }

}
