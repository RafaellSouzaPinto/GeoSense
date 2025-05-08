package com.geosense.geosense.dto;

import com.geosense.geosense.entity.TiposDefeitos;

public class DefeitoDTO {
    private Long id;
    private TiposDefeitos tiposDefeitos;
    private String descricao;
    private Long motoId;

    public DefeitoDTO() {}

    public DefeitoDTO(Long id, TiposDefeitos tiposDefeitos, String descricao, Long motoId) {
        this.id = id;
        this.tiposDefeitos = tiposDefeitos;
        this.descricao = descricao;
        this.motoId = motoId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TiposDefeitos getTiposDefeitos() { return tiposDefeitos; }
    public void setTiposDefeitos(TiposDefeitos tiposDefeitos) { this.tiposDefeitos = tiposDefeitos; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }
}
