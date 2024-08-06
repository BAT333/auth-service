package com.example.service.auth.controller;

import com.example.service.auth.model.DataLoginDTO;
import com.example.service.auth.model.DataToken;
import com.example.service.auth.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    @Transactional
    public ResponseEntity<DataToken> login(@RequestBody @Valid DataLoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DataLoginDTO> register(@RequestBody @Valid DataLoginDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }
}
