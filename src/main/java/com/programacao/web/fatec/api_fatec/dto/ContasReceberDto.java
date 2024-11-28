package com.programacao.web.fatec.api_fatec.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasReceberDto {

    private Long id;
    private LocalDate emissao;
    private LocalDate vencimento;
    private Integer clienteId;
    private BigDecimal valor;

    // Construtor vazio
    public ContasReceberDto() {
    }

    // Construtor com parâmetros
    public ContasReceberDto(Long id, LocalDate emissao, LocalDate vencimento, Integer clienteId, BigDecimal valor) {
        this.id = id;
        this.emissao = emissao;
        this.vencimento = vencimento;
        this.clienteId = clienteId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
