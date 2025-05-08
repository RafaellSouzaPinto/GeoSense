package com.geosense.geosense.dto;

import java.time.LocalDateTime;

public class AlocacaoMotoDTO {
    private Long id;
    private Long motoId;
    private Long vagaId;
    private Long mecanicoResponsavelId;
    private LocalDateTime dataHoraAlocacao;

    public AlocacaoMotoDTO() {}

    public AlocacaoMotoDTO(Long id, Long motoId, Long vagaId, Long mecanicoResponsavelId, LocalDateTime dataHoraAlocacao) {
        this.id = id;
        this.motoId = motoId;
        this.vagaId = vagaId;
        this.mecanicoResponsavelId = mecanicoResponsavelId;
        this.dataHoraAlocacao = dataHoraAlocacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }

    public Long getMecanicoResponsavelId() { return mecanicoResponsavelId; }
    public void setMecanicoResponsavelId(Long mecanicoResponsavelId) { this.mecanicoResponsavelId = mecanicoResponsavelId; }

    public LocalDateTime getDataHoraAlocacao() { return dataHoraAlocacao; }
    public void setDataHoraAlocacao(LocalDateTime dataHoraAlocacao) { this.dataHoraAlocacao = dataHoraAlocacao; }
}
