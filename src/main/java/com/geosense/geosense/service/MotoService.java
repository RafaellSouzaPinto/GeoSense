package com.geosense.geosense.service;

import com.geosense.geosense.dto.MotoDTO;
import com.geosense.geosense.entity.Moto;
import com.geosense.geosense.entity.Vaga;
import com.geosense.geosense.repository.MotoRepository;
import com.geosense.geosense.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public MotoDTO registrar(MotoDTO dto) {
        Moto moto = new Moto();
        moto.setModelo(dto.getModelo());
        moto.setPlaca(dto.getPlaca());
        moto.setChassi(dto.getChassi());
        moto.setProblemaIdentificado(dto.getProblemaIdentificado());

        if (dto.getVagaId() != null) {
            Vaga vaga = vagaRepository.findById(dto.getVagaId())
                    .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
            moto.setVaga(vaga);
            vaga.setMoto(moto);
        }

        Moto salva = motoRepository.save(moto);
        return new MotoDTO(salva.getId(), salva.getModelo(), salva.getPlaca(), salva.getChassi(),
                salva.getProblemaIdentificado(), salva.getVaga() != null ? salva.getVaga().getId() : null);
    }

    public List<MotoDTO> listar() {
        return motoRepository.findAll().stream()
                .map(m -> new MotoDTO(m.getId(), m.getModelo(), m.getPlaca(), m.getChassi(),
                        m.getProblemaIdentificado(), m.getVaga() != null ? m.getVaga().getId() : null))
                .collect(Collectors.toList());
    }

    public void remover(Long id) {
        motoRepository.deleteById(id);
    }

    public List<Vaga> vagasDisponiveisPorTipo(String tipo) {
        return vagaRepository.findAll().stream()
                .filter(v -> v.getTipo().name().equalsIgnoreCase(tipo) && v.getStatus().name().equals("DISPONIVEL"))
                .collect(Collectors.toList());
    }
}
