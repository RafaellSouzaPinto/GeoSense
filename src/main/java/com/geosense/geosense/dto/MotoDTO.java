package com.geosense.geosense.dto;

public class MotoDTO {
    private Long id;
    private String modelo;
    private String placa;
    private String chassi;
    private String problemaIdentificado;
    private Long vagaId;

    public MotoDTO() {}

    public MotoDTO(Long id, String modelo, String placa, String chassi, String problemaIdentificado, Long vagaId) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.chassi = chassi;
        this.problemaIdentificado = problemaIdentificado;
        this.vagaId = vagaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getProblemaIdentificado() {
        return problemaIdentificado;
    }

    public void setProblemaIdentificado(String problemaIdentificado) {
        this.problemaIdentificado = problemaIdentificado;
    }

    public Long getVagaId() {
        return vagaId;
    }

    public void setVagaId(Long vagaId) {
        this.vagaId = vagaId;
    }
}
