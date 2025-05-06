package com.geosense.geosense.repository;

import com.geosense.geosense.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findByEmailAndSenha(String email, String senha);
}
