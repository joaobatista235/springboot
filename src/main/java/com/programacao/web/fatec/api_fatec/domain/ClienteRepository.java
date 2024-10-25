package com.programacao.web.fatec.api_fatec.domain;
import com.programacao.web.fatec.api_fatec.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
