package com.youneskarir.demo.controller;


import com.youneskarir.demo.config.AuthenticationService;
import com.youneskarir.demo.dto.AuthenticationRequest;
import com.youneskarir.demo.dto.AuthenticationResponse;
import com.youneskarir.demo.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody(required = true) @Valid AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request, 
            HttpServletResponse response
            ) throws IOException {
        authenticationService.refreshToken(request,response);
    }
}
