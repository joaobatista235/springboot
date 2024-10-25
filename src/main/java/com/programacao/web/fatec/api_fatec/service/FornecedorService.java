package com.programacao.web.fatec.api_fatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programacao.web.fatec.api_fatec.domain.FornecedorRepository;
import com.programacao.web.fatec.api_fatec.entities.Fornecedor;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(int id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(int id) {
        fornecedorRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return fornecedorRepository.existsById(id);
    }
    
}
