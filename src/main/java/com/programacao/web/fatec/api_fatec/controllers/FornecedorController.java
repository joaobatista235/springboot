package com.programacao.web.fatec.api_fatec.controllers;

import com.programacao.web.fatec.api_fatec.dto.FornecedorDto;
import com.programacao.web.fatec.api_fatec.entities.Fornecedor;
import com.programacao.web.fatec.api_fatec.service.FornecedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // Método para criar um novo fornecedor
    @PostMapping
    public ResponseEntity<FornecedorDto> criar(@RequestBody FornecedorDto fornecedorDto) {
        try {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(fornecedorDto.getNome());

            Fornecedor savedFornecedor = fornecedorService.salvar(fornecedor);

            FornecedorDto savedFornecedorDto = new FornecedorDto(savedFornecedor.getId(), savedFornecedor.getNome());

            return new ResponseEntity<>(savedFornecedorDto, HttpStatus.CREATED);
        } catch (Throwable th) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Método para listar todos os fornecedores
    @GetMapping
    public ResponseEntity<List<FornecedorDto>> listarTodos() {
        try {
            List<Fornecedor> fornecedores = fornecedorService.listarTodos();

            List<FornecedorDto> fornecedoresDto = fornecedores.stream()
                .map(fornecedor -> new FornecedorDto(fornecedor.getId(), fornecedor.getNome()))
                .collect(Collectors.toList());

            return new ResponseEntity<>(fornecedoresDto, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para buscar um fornecedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> buscarPorId(@PathVariable int id) {
        try {
            if (!fornecedorService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
                
                FornecedorDto fornecedorDto = new FornecedorDto(fornecedor.get().getId(), fornecedor.get().getNome());

                return new ResponseEntity<>(fornecedorDto, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para atualizar um fornecedor
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody FornecedorDto fornecedorDto) {
        try {
            if (!fornecedorService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(id);
                fornecedor.setNome(fornecedorDto.getNome());

                fornecedorService.salvar(fornecedor);

                return new ResponseEntity<>("Fornecedor atualizado com sucesso!", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para deletar um fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        try {
            if (!fornecedorService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                fornecedorService.deletar(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
