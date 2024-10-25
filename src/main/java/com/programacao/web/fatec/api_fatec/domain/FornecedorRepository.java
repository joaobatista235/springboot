package com.programacao.web.fatec.api_fatec.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import com.programacao.web.fatec.api_fatec.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
