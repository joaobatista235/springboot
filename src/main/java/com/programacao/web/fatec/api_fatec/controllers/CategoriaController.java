package com.programacao.web.fatec.api_fatec.controllers;

import com.programacao.web.fatec.api_fatec.dto.CategoriaDto;
import com.programacao.web.fatec.api_fatec.entities.Categoria;
import com.programacao.web.fatec.api_fatec.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody CategoriaDto categoriaDto) {
        if (categoriaDto.getDescricao() == null || categoriaDto.getDescricao().isEmpty()) {
            return new ResponseEntity<>("Descrição da categoria é obrigatória.", HttpStatus.BAD_REQUEST);
        }

        if (categoriaService.existsByDescricao(categoriaDto.getDescricao())) {
            return new ResponseEntity<>("A descrição da categoria já está em uso.", HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaDto.getDescricao());
        categoria.setAtivo(categoriaDto.getAtivo() == null ? true : categoriaDto.getAtivo());

        categoriaService.salvar(categoria);

        return new ResponseEntity<>("Categoria criada com sucesso.", HttpStatus.CREATED);
    }
}
