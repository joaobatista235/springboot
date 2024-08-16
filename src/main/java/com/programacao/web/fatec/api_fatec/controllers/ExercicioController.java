package com.programacao.web.fatec.api_fatec.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {
    
    @GetMapping
    public String HelloWorld() {
        return "Hello";
    }
}
