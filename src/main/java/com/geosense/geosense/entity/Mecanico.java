package com.geosense.geosense.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@JsonIgnoreProperties({"gerenteResponsavel"})
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank @Size(min = 6)
    private String senha;

    @ManyToOne
    @NotNull
    private Gerente gerenteResponsavel;

    @OneToMany(mappedBy = "mecanicoResponsavel")
    private List<AlocacaoMoto> alocacoes;

    @OneToMany(mappedBy = "mecanicoResponsavel")
    private List<Moto> motosCadastradas;

    public Mecanico() {
    }

    public Mecanico(Long id, String nome, String email, String senha, Gerente gerenteResponsavel, List<AlocacaoMoto> alocacoes, List<Moto> motosCadastradas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.gerenteResponsavel = gerenteResponsavel;
        this.alocacoes = alocacoes;
        this.motosCadastradas = motosCadastradas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Gerente getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(Gerente gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public List<AlocacaoMoto> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(List<AlocacaoMoto> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public List<Moto> getMotosCadastradas() {
        return motosCadastradas;
    }

    public void setMotosCadastradas(List<Moto> motosCadastradas) {
        this.motosCadastradas = motosCadastradas;
    }
}
