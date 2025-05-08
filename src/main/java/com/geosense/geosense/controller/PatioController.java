package com.geosense.geosense.controller;

import com.geosense.geosense.dto.PatioDTO;
import com.geosense.geosense.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @PostMapping
    public ResponseEntity<PatioDTO> criar(@RequestBody PatioDTO dto) {
        PatioDTO criado = patioService.criarPatio(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioDTO> buscarPorId(@PathVariable Long id) {
        return patioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PatioDTO> listar() {
        return patioService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
