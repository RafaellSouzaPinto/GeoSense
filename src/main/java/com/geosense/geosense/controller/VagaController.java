package com.geosense.geosense.controller;


import com.geosense.geosense.dto.VagaDTO;
import com.geosense.geosense.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    public ResponseEntity<VagaDTO> criar(@RequestBody VagaDTO dto) {
        return ResponseEntity.ok(vagaService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> buscar(@PathVariable Long id) {
        return vagaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<VagaDTO> listar() {
        return vagaService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}