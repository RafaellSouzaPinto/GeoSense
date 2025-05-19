package com.geosense.geosense.controller;

import com.geosense.geosense.dto.MotoDTO;
import com.geosense.geosense.entity.Vaga;
import com.geosense.geosense.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoDTO> registrar(@RequestBody MotoDTO dto) {
        return ResponseEntity.ok(motoService.registrar(dto));
    }

    @GetMapping
    public List<MotoDTO> listar() {
        return motoService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
