package com.programacao.web.fatec.api_fatec.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasPagarDto {

    private Long id;
    private LocalDate emissao;
    private LocalDate vencimento;
    private int fornecedorId;
    private BigDecimal valor;

    // Construtor com todos os parâmetros
    public ContasPagarDto(Long id, LocalDate emissao, LocalDate vencimento, int fornecedorId, BigDecimal valor) {
        this.id = id;
        this.emissao = emissao;
        this.vencimento = vencimento;
        this.fornecedorId = fornecedorId;
        this.valor = valor;
    }

    // Getters e setters
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

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
