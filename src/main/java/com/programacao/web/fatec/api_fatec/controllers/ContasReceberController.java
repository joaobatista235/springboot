package com.programacao.web.fatec.api_fatec.controllers;

import com.programacao.web.fatec.api_fatec.dto.ContasReceberDto;
import com.programacao.web.fatec.api_fatec.entities.Cliente;
import com.programacao.web.fatec.api_fatec.entities.ContasReceber;
import com.programacao.web.fatec.api_fatec.service.ClienteService;
import com.programacao.web.fatec.api_fatec.service.ContasReceberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contas_receber")
public class ContasReceberController {

    @Autowired
    private ContasReceberService contasService;

    @Autowired
    private ClienteService clienteService;

    // Criar conta a receber
    // Criar conta a receber
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContasReceberDto contasReceberDto) {
        try {
            if (contasReceberDto.getEmissao().isAfter(contasReceberDto.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }
            if (contasReceberDto.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a receber deve ser positivo.", HttpStatus.BAD_REQUEST);
            }
            if (!clienteService.existsById(contasReceberDto.getClienteId())) {
                return new ResponseEntity<>("Cliente não existe.", HttpStatus.BAD_REQUEST);
            }

            ContasReceber contasReceber = new ContasReceber();
            contasReceber.setEmissao(contasReceberDto.getEmissao());
            contasReceber.setVencimento(contasReceberDto.getVencimento());
            contasReceber.setValor(contasReceberDto.getValor());

            Optional<Cliente> clienteOptional = clienteService.buscarPorId(contasReceberDto.getClienteId());
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                contasReceber.setCliente(cliente);
            } else {
                return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.BAD_REQUEST);
            }

            ContasReceber contaSalva = contasService.salvar(contasReceber);

            ContasReceberDto respostaDto = new ContasReceberDto(
                    contaSalva.getId(),
                    contaSalva.getEmissao(),
                    contaSalva.getVencimento(),
                    contaSalva.getCliente().getId(),
                    contaSalva.getValor());

            return new ResponseEntity<>(respostaDto, HttpStatus.CREATED);
        } catch (Throwable th) {
            return new ResponseEntity<>("Erro ao criar conta a receber.", HttpStatus.BAD_REQUEST);
        }
    }

    // Listar todas as contas a receber
    @GetMapping
    public ResponseEntity<List<ContasReceberDto>> listarTodos() {
        try {
            List<ContasReceber> contas = contasService.listarTodos();
            List<ContasReceberDto> contasDto = contas.stream()
                    .map(conta -> new ContasReceberDto(
                            conta.getId(),
                            conta.getEmissao(),
                            conta.getVencimento(),
                            conta.getCliente().getId(),
                            conta.getValor()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(contasDto, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar conta por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContasReceberDto> buscarPorId(@PathVariable Long id) {
        try {
            Optional<ContasReceber> conta = contasService.buscarPorId(id);
            if (conta.isPresent()) {
                ContasReceber contaEntity = conta.get();
                ContasReceberDto contaDto = new ContasReceberDto(
                        contaEntity.getId(),
                        contaEntity.getEmissao(),
                        contaEntity.getVencimento(),
                        contaEntity.getCliente().getId(),
                        contaEntity.getValor());
                return new ResponseEntity<>(contaDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Atualizar conta a receber
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ContasReceberDto contasReceberDto) {
        try {
            if (contasReceberDto.getEmissao().isAfter(contasReceberDto.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }

            if (contasReceberDto.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a receber deve ser positivo.", HttpStatus.BAD_REQUEST);
            }

            if (!clienteService.existsById(contasReceberDto.getClienteId())) {
                return new ResponseEntity<>("Cliente não existe.", HttpStatus.BAD_REQUEST);
            }

            if (!contasService.existsById(id)) {
                return new ResponseEntity<>("Conta a receber não encontrada.", HttpStatus.NOT_FOUND);
            }

            ContasReceber conta = new ContasReceber();
            conta.setId(id);
            conta.setEmissao(contasReceberDto.getEmissao());
            conta.setVencimento(contasReceberDto.getVencimento());
            conta.setValor(contasReceberDto.getValor());

            Optional<Cliente> clienteOptional = clienteService.buscarPorId(contasReceberDto.getClienteId());
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                conta.setCliente(cliente);
            } else {
                return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.BAD_REQUEST);
            }

            contasService.salvar(conta);
            return new ResponseEntity<>("Conta a receber atualizada com sucesso!", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Erro interno ao atualizar a conta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Deletar conta a receber
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
