package com.geosense.geosense.repository;

import com.geosense.geosense.entity.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {
    Optional<Mecanico> findByEmailAndSenha(String email, String senha);
}