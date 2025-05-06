package com.geosense.geosense.controller;

import com.geosense.geosense.dto.CredentialsDTO;
import com.geosense.geosense.dto.MecanicoDTO;
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

    public MecanicoController(MecanicoService mecanicoService, GerenteService gerenteService) {
        this.mecanicoService = mecanicoService;
        this.gerenteService = gerenteService;
    }

    @PostMapping
    public ResponseEntity<Mecanico> registrar(@RequestBody MecanicoDTO dto) {
        Mecanico criado = mecanicoService.registerMecanico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO cred) {
        if ("administrador".equalsIgnoreCase(cred.getEmail()) && "mottu@gmail.com".equals(cred.getEmail()) && "Geosense@2025".equals(cred.getSenha())) {
            return ResponseEntity.ok(new MecanicoDTO("Administrador", "mottu@gmail.com", "Geosense@2025"));
        }

        return mecanicoService.loginMecanico(cred.getEmail(), cred.getSenha())
                .map(mecanico -> ResponseEntity.ok(new MecanicoDTO(mecanico.getNome(), mecanico.getEmail(), mecanico.getSenha()))) // Retorna MecanicoDTO
                .orElseGet(() -> {
                    MecanicoDTO novoMecanico = new MecanicoDTO(cred.getEmail(), cred.getEmail(), cred.getSenha());
                    mecanicoService.registerMecanico(novoMecanico);

                    return ResponseEntity.status(HttpStatus.CREATED).body(new MecanicoDTO(novoMecanico.getNome(), novoMecanico.getEmail(), novoMecanico.getSenha()));
                });
    }



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
    public ResponseEntity<Mecanico> atualizar(@PathVariable Long id, @RequestBody MecanicoDTO dto) {
        Mecanico atualizado = mecanicoService.atualizarMecanico(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        mecanicoService.deletarMecanico(id);
        return ResponseEntity.noContent().build();
    }
}
