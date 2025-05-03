package com.geosense.geosense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class AlocacaoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Moto moto;

    @ManyToOne
    @NotNull
    private Vaga vaga;

    @ManyToOne
    @NotNull
    private Mecanico mecanicoResponsavel;

    private LocalDateTime dataHoraAlocacao;

    public AlocacaoMoto() {
    }

    public AlocacaoMoto(Long id, Moto moto, Vaga vaga, Mecanico mecanicoResponsavel, LocalDateTime dataHoraAlocacao) {
        this.id = id;
        this.moto = moto;
        this.vaga = vaga;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.dataHoraAlocacao = dataHoraAlocacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Mecanico getMecanicoResponsavel() {
        return mecanicoResponsavel;
    }

    public void setMecanicoResponsavel(Mecanico mecanicoResponsavel) {
        this.mecanicoResponsavel = mecanicoResponsavel;
    }

    public LocalDateTime getDataHoraAlocacao() {
        return dataHoraAlocacao;
    }

    public void setDataHoraAlocacao(LocalDateTime dataHoraAlocacao) {
        this.dataHoraAlocacao = dataHoraAlocacao;
    }

    @Override
    public String toString() {
        return "AlocacaoMoto{" +
                "id=" + id +
                ", moto=" + moto +
                ", vaga=" + vaga +
                ", mecanicoResponsavel=" + mecanicoResponsavel +
                ", dataHoraAlocacao=" + dataHoraAlocacao +
                '}';
    }
}
