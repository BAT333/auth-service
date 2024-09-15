package com.example.service.auth.controller;

import com.example.service.auth.model.DataLoginDTO;
import com.example.service.auth.model.DataToken;
import com.example.service.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "http://172.27.64.1:8082")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    @Transactional
    @Operation(summary ="User login", description = "User enters their registered credentials and receives a token back allowing access to the necessary information.")

    public ResponseEntity<DataToken> login(@RequestBody @Valid DataLoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }
    @PostMapping("/register")
    @Transactional
    @Operation(summary ="Registers new users", description = "Register a user with the necessary information and send it for validation")
    public ResponseEntity<DataLoginDTO> register(@RequestBody @Valid DataLoginDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }
    @GetMapping("/valid/{token}")
    public void validation(@PathVariable String token, HttpServletResponse response){
        this.service.valid(token,response);
    }
}
