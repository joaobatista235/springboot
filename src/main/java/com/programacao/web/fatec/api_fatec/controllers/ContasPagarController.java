package com.programacao.web.fatec.api_fatec.controllers;

import com.programacao.web.fatec.api_fatec.dto.ContasPagarDto;
import com.programacao.web.fatec.api_fatec.entities.ContasPagar;
import com.programacao.web.fatec.api_fatec.entities.Fornecedor;
import com.programacao.web.fatec.api_fatec.service.ContasPagarService;
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
@RequestMapping("/api/contas_pagar")
public class ContasPagarController {

    @Autowired
    private ContasPagarService contasService;

    @Autowired
    private FornecedorService fornecedorService;

    // Método para criar conta a pagar
    @PostMapping
    public ResponseEntity<String> criar(@RequestBody ContasPagarDto contaDto) {
        try {
            if (contaDto.getEmissao().isAfter(contaDto.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }
            if (contaDto.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a pagar deve ser positivo.", HttpStatus.BAD_REQUEST);
            }
            if (!fornecedorService.existsById(contaDto.getFornecedorId())) {
                return new ResponseEntity<>("Fornecedor não existe.", HttpStatus.BAD_REQUEST);
            }
            
            // Obter o fornecedor pelo ID
            Fornecedor fornecedor = fornecedorService.buscarPorId(contaDto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

            // Converter DTO para entidade
            ContasPagar conta = new ContasPagar();
            conta.setEmissao(contaDto.getEmissao());
            conta.setVencimento(contaDto.getVencimento());
            conta.setValor(contaDto.getValor());
            conta.setFornecedor(fornecedor);

            contasService.salvar(conta);
            return new ResponseEntity<>("Conta a pagar criada com sucesso.", HttpStatus.CREATED);
        } catch (Throwable th) {
            return new ResponseEntity<>("Erro ao criar conta a pagar.", HttpStatus.BAD_REQUEST);
        }
    }

    // Método para listar todas as contas a pagar
    @GetMapping
    public ResponseEntity<List<ContasPagarDto>> listarTodos() {
        try {
            List<ContasPagar> contas = contasService.listarTodos();
            List<ContasPagarDto> contasDtos = contas.stream()
                    .map(conta -> new ContasPagarDto(conta.getId(), conta.getEmissao(), conta.getVencimento(),
                            conta.getFornecedor().getId(), conta.getValor()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(contasDtos, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para buscar conta a pagar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContasPagarDto> buscarPorId(@PathVariable Long id) {
        try {
            if (!contasService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Optional<ContasPagar> conta = contasService.buscarPorId(id);
                ContasPagarDto contaDto = new ContasPagarDto(conta.get().getId(), conta.get().getEmissao(),
                        conta.get().getVencimento(), conta.get().getFornecedor().getId(), conta.get().getValor());
                return new ResponseEntity<>(contaDto, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para atualizar conta a pagar
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ContasPagarDto contaDto) {
        try {
            if (contaDto.getEmissao().isAfter(contaDto.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }
            if (contaDto.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a pagar deve ser positivo.", HttpStatus.BAD_REQUEST);
            }
            if (!fornecedorService.existsById(contaDto.getFornecedorId())) {
                return new ResponseEntity<>("Fornecedor não existe.", HttpStatus.BAD_REQUEST);
            }
            if (!contasService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // Obter fornecedor
                Fornecedor fornecedor = fornecedorService.buscarPorId(contaDto.getFornecedorId())
                        .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

                ContasPagar conta = new ContasPagar();
                conta.setId(id);
                conta.setEmissao(contaDto.getEmissao());
                conta.setVencimento(contaDto.getVencimento());
                conta.setValor(contaDto.getValor());
                conta.setFornecedor(fornecedor);
                
                contasService.salvar(conta);
                return new ResponseEntity<>("Conta a pagar atualizada com sucesso!", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para deletar conta a pagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            if (!contasService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                contasService.deletar(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
