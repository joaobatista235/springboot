package com.programacao.web.fatec.api_fatec.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.programacao.web.fatec.api_fatec.domain.ContasReceberRepository;
import com.programacao.web.fatec.api_fatec.entities.ContasReceber;

@Service
public class ContasReceberService {

    @Autowired
    private ContasReceberRepository contasRepository;

    public List<ContasReceber> listarTodos() {
        return contasRepository.findAll();
    }

    public Optional<ContasReceber> buscarPorId(Long id) {
        return contasRepository.findById(id);
    }

    public ContasReceber salvar(ContasReceber cliente) {
        return contasRepository.save(cliente);
    }

    public void deletar(Long id) {
        contasRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return contasRepository.existsById(id);
    }

}
