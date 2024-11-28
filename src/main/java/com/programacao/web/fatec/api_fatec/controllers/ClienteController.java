package com.programacao.web.fatec.api_fatec.controllers;

import com.programacao.web.fatec.api_fatec.dto.ClienteDto;
import com.programacao.web.fatec.api_fatec.entities.Cliente;
import com.programacao.web.fatec.api_fatec.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Método para criar cliente
    @PostMapping
    public ResponseEntity<ClienteDto> criar(@RequestBody ClienteDto clienteDto) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteDto.getNome());

            Cliente clienteSalvo = clienteService.salvar(cliente);

            ClienteDto clienteResposta = new ClienteDto(clienteSalvo.getId(), clienteSalvo.getNome());

            return new ResponseEntity<>(clienteResposta, HttpStatus.CREATED);
        } catch (Throwable th) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Método para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarTodos() {
        try {
            List<Cliente> clientes = clienteService.listarTodos();
            List<ClienteDto> clienteDtos = clientes.stream()
                    .map(cliente -> new ClienteDto(cliente.getId(), cliente.getNome()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(clienteDtos, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable int id) {
        try {
            if (!clienteService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Optional<Cliente> cliente = clienteService.buscarPorId(id);
                ClienteDto clienteDto = new ClienteDto(cliente.get().getId(), cliente.get().getNome());

                return new ResponseEntity<>(clienteDto, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para atualizar cliente
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody ClienteDto clienteDto) {
        try {
            if (!clienteService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(clienteDto.getNome());

                clienteService.salvar(cliente);

                return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        try {
            if (!clienteService.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                clienteService.deletar(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
