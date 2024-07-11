package com.example.E_bank.controller;

import com.example.E_bank.Dto.LoginRequest;
import com.example.E_bank.Dto.SignupRequest;
import com.example.E_bank.config.JwtHelper;
import com.example.E_bank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {
    @Autowired
    AuthenticationManager authenticationManager;


    private final UserService userService;

    public AuthentificationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        String token= JwtHelper.generateToken(request.username());
        return ResponseEntity.ok(token);
    }
}


