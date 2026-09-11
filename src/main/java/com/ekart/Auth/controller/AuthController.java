package com.ekart.Auth.controller;

import com.ekart.Auth.entity.User;
import com.ekart.Auth.exception.BadCredentialsException;
import com.ekart.Auth.repository.UserRepository;
import com.ekart.Auth.service.JwtService;
import com.nimbusds.jose.JOSEException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final JwtService jwtService;

    public AuthController(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws JOSEException
    {
        User user= userRepository.findByUserName(request.username).orElseThrow(()-> new BadCredentialsException("Invalid credentials"));

        if(!passwordEncoder.matches(request.password, user.getUserpassword()))
        {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String token= jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("access-token", token));

    }

    record  LoginRequest(String username, String password){}

}
