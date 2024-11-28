package com.programacao.web.fatec.api_fatec.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.programacao.web.fatec.api_fatec.domain.ContasPagarRepository;
import com.programacao.web.fatec.api_fatec.entities.ContasPagar;

@Service
public class ContasPagarService {

    @Autowired
    private ContasPagarRepository contasRepository;

    public List<ContasPagar> listarTodos() {
        return contasRepository.findAll();
    }

    public Optional<ContasPagar> buscarPorId(Long id) {
        return contasRepository.findById(id);
    }

    public ContasPagar salvar(ContasPagar cliente) {
        return contasRepository.save(cliente);
    }

    public void deletar(Long id) {
        contasRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return contasRepository.existsById(id);
    }

}
