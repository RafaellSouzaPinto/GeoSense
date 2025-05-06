package com.geosense.geosense.controller;

import com.geosense.geosense.dto.CredentialsDTO;
import com.geosense.geosense.dto.MecanicoRegistrationDTO;
import com.geosense.geosense.entity.Mecanico;
import com.geosense.geosense.service.GerenteService;
import com.geosense.geosense.service.MecanicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final MecanicoService mecanicoService;
    private final GerenteService gerenteService;

    public MecanicoController(MecanicoService mecanicoService,
                              GerenteService gerenteService) {
        this.mecanicoService = mecanicoService;
        this.gerenteService  = gerenteService;
    }

    /** 1) Registro de mecânico, sem gerenteId no body */
    @PostMapping
    public ResponseEntity<Mecanico> registrar(@RequestBody MecanicoRegistrationDTO dto) {
        Mecanico criado = mecanicoService.registerMecanico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    /** 2) Login (já implementado) */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO cred) {
        if (gerenteService.loginGerente(cred.getEmail(), cred.getSenha())) {
            return ResponseEntity.ok("Logado como GERENTE");
        }
        return mecanicoService.loginMecanico(cred.getEmail(), cred.getSenha())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Credenciais inválidas"));
    }

    /** 3) Listar, buscar por ID, atualizar e deletar continuam iguais… */
    @GetMapping
    public ResponseEntity<List<Mecanico>> listar() {
        return ResponseEntity.ok(mecanicoService.listarMecanicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mecanico> buscarPorId(@PathVariable Long id) {
        return mecanicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mecanico> atualizar(@PathVariable Long id,
                                              @RequestBody MecanicoRegistrationDTO dto) {
        // você pode usar o mesmo DTO de registro para atualizar nome/email/senha
        Mecanico atualizado = mecanicoService.atualizarMecanico(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        mecanicoService.deletarMecanico(id);
        return ResponseEntity.noContent().build();
    }
}
