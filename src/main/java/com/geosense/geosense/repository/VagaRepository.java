package com.geosense.geosense.repository;

import com.geosense.geosense.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    boolean existsByNumeroAndPatioId(int numero, Long patioId);

}
