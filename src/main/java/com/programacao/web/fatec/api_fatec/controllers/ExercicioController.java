package com.programacao.web.fatec.api_fatec.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {
    
    @GetMapping
    public String HelloWorld() {
        return "Hello";
    }

    @GetMapping("/hello1")
    public String HelloWorld1() {
        return "Hello1";
    }

    @GetMapping("/reverter-nome/{nome}")
    String reverterNome(@PathVariable String nome) {
        return new StringBuilder(nome).reverse().toString();
    }

    @GetMapping("/par-ou-impar/{numero}")
    String verificarParOuImpar(@PathVariable Integer numero) {
        if (numero % 2 == 0) {
            return "Par";
        } else {
            return "Ímpar";
        }
    }

    @GetMapping("/contar-letras/{texto}")
    String contarLetras(@PathVariable String texto) {
        int qtdLetras = texto.length();
        return "A palavra digitada possui " + qtdLetras + " letras.";
    }

    @GetMapping("/idade-com-parametro-tipo-integer/{paramIdade}")
    String idade(@PathVariable Integer paramIdade) {
        try {
            if(paramIdade < 0){
                throw new NumberFormatException();
            }
    
            if(paramIdade < 12){
                return "CRIANCA";
            } else if (paramIdade <= 18) {
                return "ADOLESCENTE";
            } else if (paramIdade <= 60) {
                return "ADULTO";
            } else {
                return "IDOSO";
            }
        } catch (NumberFormatException ex) {
            return "Idade inválida";
        }
    }
}
