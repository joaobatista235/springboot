package com.programacao.web.fatec.api_fatec.domain.cliente;

import com.programacao.web.fatec.api_fatec.entities.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNome(String nome);
}
