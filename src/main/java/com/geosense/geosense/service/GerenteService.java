package com.geosense.geosense.service;

import com.geosense.geosense.repository.GerenteRepository;
import org.springframework.stereotype.Service;

@Service
public class GerenteService {

    private final GerenteRepository gerenteRepository;

    public GerenteService(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }

    public boolean loginGerente(String email, String senha) {
        // 1️⃣ Primeiro checa a credencial “root”
        if ("mottu@gmail.com".equals(email) && "Geosense@2025".equals(senha)) {
            return true;
        }
        // 2️⃣ Se não for a “root”, tenta no banco
        return gerenteRepository.findByEmailAndSenha(email, senha)
                .isPresent();
    }
}
