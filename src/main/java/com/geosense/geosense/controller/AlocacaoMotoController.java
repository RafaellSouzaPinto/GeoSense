package com.geosense.geosense.controller;

import com.geosense.geosense.dto.AlocacaoMotoDTO;
import com.geosense.geosense.service.AlocacaoMotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoMotoController {

    @Autowired
    private AlocacaoMotoService alocacaoMotoService;

    @PostMapping
    public ResponseEntity<AlocacaoMotoDTO> alocar(@RequestBody AlocacaoMotoDTO dto) {
        return ResponseEntity.ok(alocacaoMotoService.alocar(dto));
    }

    @GetMapping
    public List<AlocacaoMotoDTO> listar() {
        return alocacaoMotoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlocacaoMotoDTO> buscar(@PathVariable Long id) {
        return alocacaoMotoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alocacaoMotoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
