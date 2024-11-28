package com.programacao.web.fatec.api_fatec.domain;

import com.programacao.web.fatec.api_fatec.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByDescricao(String descricao);
}
