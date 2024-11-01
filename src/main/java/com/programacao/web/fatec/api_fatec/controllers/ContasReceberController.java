package com.programacao.web.fatec.api_fatec.controllers;
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

@RestController
@RequestMapping("/api/contas_receber")
public class ContasReceberController {

    @Autowired
    private ContasReceberService contasService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody ContasReceber conta) {
        try {
            if (conta.getEmissao().isAfter(conta.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }
            if (conta.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a pagar deve ser positivo.", HttpStatus.BAD_REQUEST);
            }
            if (!clienteService.existsById(conta.getCliente().getId())) {
                return new ResponseEntity<>("Cliente não existe.", HttpStatus.BAD_REQUEST);
            }
            contasService.salvar(conta);
            return new ResponseEntity<>("Conta a receber criada com sucesso.", HttpStatus.CREATED);
        } catch (Throwable th) {
            return new ResponseEntity<>("Erro ao criar conta a receber.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ContasReceber>> listarTodos() {
        try {
            List<ContasReceber> conta = contasService.listarTodos();
            return new ResponseEntity<>(conta, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContasReceber> buscarPorId(@PathVariable int id) {
        try {
            if (!contasService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Optional<ContasReceber> conta = contasService.buscarPorId(id);
                return new ResponseEntity<>(conta.get(), HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody ContasReceber conta) {
        try {
            if (conta.getEmissao().isAfter(conta.getVencimento())) {
                return new ResponseEntity<>("A data de emissão não pode ser posterior à data de vencimento.",
                        HttpStatus.BAD_REQUEST);
            }
            if (conta.getValor().signum() <= 0) {
                return new ResponseEntity<>("O valor da conta a pagar deve ser positivo.", HttpStatus.BAD_REQUEST);
            }
            if (!clienteService.existsById(conta.getCliente().getId())) {
                return new ResponseEntity<>("Cliente não existe.", HttpStatus.BAD_REQUEST);
            }
            if (!contasService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                conta.setId((long)id);
                contasService.salvar(conta);
                return new ResponseEntity<>("Conta a receber atualizado com sucesso!", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
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
