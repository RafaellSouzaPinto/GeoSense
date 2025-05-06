package com.geosense.geosense.controller;

import com.geosense.geosense.dto.CredentialsDTO;
import com.geosense.geosense.service.GerenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    private final GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO cred) {
        if (gerenteService.loginGerente(cred.getEmail(), cred.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
