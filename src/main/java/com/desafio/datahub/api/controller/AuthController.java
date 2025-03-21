package com.desafio.datahub.api.controller;

import com.desafio.datahub.api.dto.AuthDTO;
import com.desafio.datahub.api.dto.LoginResponseDTO;
import com.desafio.datahub.api.dto.RegisterDTO;
import com.desafio.datahub.api.model.User;
import com.desafio.datahub.api.repository.UserRepository;
import com.desafio.datahub.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO login) {
        if (this.repository.findByEmail(login.email()) != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "O e-mail informado já está cadastrado.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(login.senha());
        User novoUsuario = new User(login.email(), login.nome(), encryptedPassword, login.role());

        this.repository.save(novoUsuario);

        Map<String, Object> response = new HashMap<>();
        response.put("id", novoUsuario.getId());
        response.put("email", novoUsuario.getEmail());

        return ResponseEntity.ok(response);
    }

}

