package com.programacao.web.fatec.api_fatec.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FornecedorDto {

    private int id;
    private String nome;

    public FornecedorDto() {
    }

    public FornecedorDto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
