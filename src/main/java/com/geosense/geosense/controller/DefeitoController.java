package com.geosense.geosense.controller;

import com.geosense.geosense.dto.DefeitoDTO;
import com.geosense.geosense.service.DefeitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/defeitos")
public class DefeitoController {

    @Autowired
    private DefeitoService defeitoService;

    @PostMapping
    public ResponseEntity<DefeitoDTO> registrar(@RequestBody DefeitoDTO dto) {
        return ResponseEntity.ok(defeitoService.registrar(dto));
    }

    @GetMapping
    public List<DefeitoDTO> listar() {
        return defeitoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefeitoDTO> buscar(@PathVariable Long id) {
        return defeitoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        defeitoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
