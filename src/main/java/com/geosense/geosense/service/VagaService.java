package com.geosense.geosense.service;

import com.geosense.geosense.dto.VagaDTO;
import com.geosense.geosense.entity.Moto;
import com.geosense.geosense.entity.Patio;
import com.geosense.geosense.entity.StatusVaga;
import com.geosense.geosense.entity.Vaga;
import com.geosense.geosense.repository.MotoRepository;
import com.geosense.geosense.repository.PatioRepository;
import com.geosense.geosense.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private MotoRepository motoRepository;

    public VagaDTO criar(VagaDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        // A verificação continua com o tipo 'int' para o numero
        if (vagaRepository.existsByNumeroAndPatioId(dto.getNumero(), dto.getPatioId())) {
            throw new RuntimeException("Já existe uma vaga com esse número nesse pátio. Escolha outro número.");
        }

        Vaga vaga = new Vaga();
        vaga.setNumero(dto.getNumero());
        vaga.setTipo(dto.getTipo());
        vaga.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusVaga.DISPONIVEL);
        vaga.setPatio(patio);

        if (dto.getMotoId() != null) {
            Moto moto = motoRepository.findById(dto.getMotoId())
                    .orElseThrow(() -> new RuntimeException("Moto não encontrada"));

            if (vaga.getMoto() != null) {
                throw new RuntimeException("Esta vaga já está ocupada.");
            }

            vaga.setMoto(moto);
            moto.setVaga(vaga);
            vaga.setStatus(StatusVaga.OCUPADA);
        }

        Vaga salva = vagaRepository.save(vaga);
        return new VagaDTO(salva.getId(), salva.getNumero(), salva.getStatus(), salva.getTipo(), patio.getId(),
                salva.getMoto() != null ? salva.getMoto().getId() : null);
    }

    public Optional<VagaDTO> buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .map(v -> new VagaDTO(v.getId(), v.getNumero(), v.getStatus(), v.getTipo(), v.getPatio().getId(),
                        v.getMoto() != null ? v.getMoto().getId() : null));
    }

    public List<VagaDTO> listarTodos() {
        return vagaRepository.findAll()
                .stream()
                .map(v -> new VagaDTO(v.getId(), v.getNumero(), v.getStatus(), v.getTipo(), v.getPatio().getId(),
                        v.getMoto() != null ? v.getMoto().getId() : null))
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        vagaRepository.deleteById(id);
    }
}
