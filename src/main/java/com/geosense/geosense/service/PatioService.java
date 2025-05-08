package com.geosense.geosense.service;

import com.geosense.geosense.dto.PatioDTO;
import com.geosense.geosense.entity.Patio;
import com.geosense.geosense.entity.Vaga;
import com.geosense.geosense.repository.PatioRepository;
import com.geosense.geosense.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public PatioDTO criarPatio(PatioDTO dto) {
        List<Vaga> vagas = dto.getVagaIds() != null
                ? vagaRepository.findAllById(dto.getVagaIds())
                : List.of();

        Patio patio = new Patio();
        patio.setVagas(vagas);

        Patio salvo = patioRepository.save(patio);

        return new PatioDTO(salvo.getId(),
                salvo.getVagas().stream().map(Vaga::getId).collect(Collectors.toList()));
    }

    public Optional<PatioDTO> buscarPorId(Long id) {
        return patioRepository.findById(id).map(patio ->
                new PatioDTO(patio.getId(),
                        patio.getVagas().stream().map(Vaga::getId).collect(Collectors.toList())));
    }

    public List<PatioDTO> listarTodos() {
        return patioRepository.findAll().stream().map(patio ->
                        new PatioDTO(patio.getId(),
                                patio.getVagas().stream().map(Vaga::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        patioRepository.deleteById(id);
    }
}
