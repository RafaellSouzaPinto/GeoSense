package com.geosense.geosense.dto;

import com.geosense.geosense.entity.StatusVaga;
import com.geosense.geosense.entity.TipoVaga;

public class VagaDTO {
    private Long id;
    private int numero;
    private StatusVaga status;
    private TipoVaga tipo;
    private Long patioId;
    private Long motoId;

    public VagaDTO() {}

    public VagaDTO(Long id, int numero, StatusVaga status, TipoVaga tipo, Long patioId, Long motoId) {
        this.id = id;
        this.numero = numero;
        this.status = status;
        this.tipo = tipo;
        this.patioId = patioId;
        this.motoId = motoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public StatusVaga getStatus() {
        return status;
    }

    public void setStatus(StatusVaga status) {
        this.status = status;
    }

    public TipoVaga getTipo() {
        return tipo;
    }

    public void setTipo(TipoVaga tipo) {
        this.tipo = tipo;
    }

    public Long getPatioId() {
        return patioId;
    }

    public void setPatioId(Long patioId) {
        this.patioId = patioId;
    }

    public Long getMotoId() {
        return motoId;
    }

    public void setMotoId(Long motoId) {
        this.motoId = motoId;
    }
}