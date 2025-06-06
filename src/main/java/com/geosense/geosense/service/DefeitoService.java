package com.geosense.geosense.service;

import com.geosense.geosense.dto.DefeitoDTO;
import com.geosense.geosense.entity.Defeito;
import com.geosense.geosense.entity.Moto;
import com.geosense.geosense.repository.DefeitoRepository;
import com.geosense.geosense.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefeitoService {

    @Autowired
    private DefeitoRepository defeitoRepository;

    @Autowired
    private MotoRepository motoRepository;

    public DefeitoDTO registrar(DefeitoDTO dto) {
        Moto moto = motoRepository.findById(dto.getMotoId())
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));

        Defeito defeito = new Defeito();
        defeito.setTiposDefeitos(dto.getTiposDefeitos());
        defeito.setDescricao(dto.getDescricao());
        defeito.setMoto(moto);

        Defeito salvo = defeitoRepository.save(defeito);

        return new DefeitoDTO(salvo.getId(), salvo.getTiposDefeitos(), salvo.getDescricao(), salvo.getMoto().getId());
    }

    public List<DefeitoDTO> listarTodos() {
        return defeitoRepository.findAll().stream()
                .map(d -> new DefeitoDTO(d.getId(), d.getTiposDefeitos(), d.getDescricao(), d.getMoto().getId()))
                .collect(Collectors.toList());
    }

    public Optional<DefeitoDTO> buscarPorId(Long id) {
        return defeitoRepository.findById(id)
                .map(d -> new DefeitoDTO(d.getId(), d.getTiposDefeitos(), d.getDescricao(), d.getMoto().getId()));
    }

    public void deletar(Long id) {
        defeitoRepository.deleteById(id);
    }
}
