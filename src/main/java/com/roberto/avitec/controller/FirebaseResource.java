package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.TokenModel;
import com.roberto.avitec.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/firebase")
public class FirebaseResource {

    private FirebaseService firebaseService;

    @Autowired
    public FirebaseResource(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping(produces="application/json")
    public RetornoBaseModel findAll() {
        return new RetornoBaseModel(true, "Lista de tokens", firebaseService.findAll());
    }

    @PostMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel create(@RequestBody TokenModel token) {
        return new RetornoBaseModel(true, "Token atualizado com sucesso", firebaseService.setToken(token));
    }

    @PutMapping(value= "/intervalo/{intervalo}", consumes = "application/json", produces="application/json")
    public RetornoBaseModel setIntervaloEnvioPush(@PathVariable Integer intervalo) {
        return new RetornoBaseModel(true, "Intervalo de envio atualizado com sucesso", firebaseService.setIntervaloEnvio(intervalo));
    }
}
