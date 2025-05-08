package com.geosense.geosense.dto;

import java.util.List;

public class PatioDTO {
    private Long id;
    private List<Long> vagaIds;

    public PatioDTO() {}

    public PatioDTO(Long id, List<Long> vagaIds) {
        this.id = id;
        this.vagaIds = vagaIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getVagaIds() {
        return vagaIds;
    }

    public void setVagaIds(List<Long> vagaIds) {
        this.vagaIds = vagaIds;
    }
}