package com.geosense.geosense.service;

import com.geosense.geosense.dto.MecanicoRegistrationDTO;
import com.geosense.geosense.entity.Gerente;
import com.geosense.geosense.entity.Mecanico;
import com.geosense.geosense.repository.GerenteRepository;
import com.geosense.geosense.repository.MecanicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MecanicoService {

    private final MecanicoRepository mecanicoRepo;
    private final GerenteRepository gerenteRepo;

    public MecanicoService(MecanicoRepository mecanicoRepo,
                           GerenteRepository gerenteRepo) {
        this.mecanicoRepo = mecanicoRepo;
        this.gerenteRepo  = gerenteRepo;
    }

    /** Novo método de registro, sem precisar do gerenteId no JSON */
    public Mecanico registerMecanico(MecanicoRegistrationDTO dto) {
        // pega sempre o gerente “root”
        Gerente gerente = gerenteRepo
                .findByEmailAndSenha("mottu@gmail.com", "Geosense@2025")
                .orElseThrow(() -> new RuntimeException("Gerente root não encontrado"));

        Mecanico m = new Mecanico();
        m.setNome(dto.getNome());
        m.setEmail(dto.getEmail());
        m.setSenha(dto.getSenha());
        m.setGerenteResponsavel(gerente);

        return mecanicoRepo.save(m);
    }

    /** Mantém seus métodos de listar/atualizar/deletar… */

    public List<Mecanico> listarMecanicos() {
        return mecanicoRepo.findAll();
    }

    public Optional<Mecanico> loginMecanico(String email, String senha) {
        return mecanicoRepo.findByEmailAndSenha(email, senha);
    }

    // …
}
