package com.programacao.web.fatec.api_fatec.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.programacao.web.fatec.api_fatec.entities.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    //http://localhost:<port>/api/usuario/register
    //POST
    //Parametro: @RequestBody => enviar no corpo da requisicao (body)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok("Bem-vindo, " + user.getName() + "! Você tem " + user.getAge() + " anos.");
    }
    
}