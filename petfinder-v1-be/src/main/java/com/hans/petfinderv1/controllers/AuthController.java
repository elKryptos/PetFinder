package com.hans.petfinderv1.controllers;

import com.hans.petfinderv1.model.dto.AuthDto;
import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.services.AuthService;
import com.hans.petfinderv1.utils.TokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenUtil tokenUtil;

    @PostMapping("/signin")
    public ResponseEntity<AuthDto> signin(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(authDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthDto> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.logout(token));
    }
}
