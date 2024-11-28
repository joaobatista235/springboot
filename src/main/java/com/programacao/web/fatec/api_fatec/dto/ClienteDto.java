package com.programacao.web.fatec.api_fatec.dto;

public class ClienteDto {

    private int id;
    private String nome;

    public ClienteDto() {
    }

    public ClienteDto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
