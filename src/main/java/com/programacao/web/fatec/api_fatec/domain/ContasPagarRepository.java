package com.programacao.web.fatec.api_fatec.domain;
import com.programacao.web.fatec.api_fatec.entities.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {
    
}
