package com.programacao.web.fatec.api_fatec.service;

import com.programacao.web.fatec.api_fatec.entities.Categoria;
import com.programacao.web.fatec.api_fatec.domain.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public boolean existsByDescricao(String descricao) {
        return categoriaRepository.existsByDescricao(descricao);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
