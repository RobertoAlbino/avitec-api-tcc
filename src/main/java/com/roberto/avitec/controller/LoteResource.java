package com.roberto.avitec.controller;

import com.roberto.avitec.business.LoteBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/lotes")
public class LoteResource {

    private LoteBusiness loteBusiness;

    @Autowired
    public LoteResource(LoteBusiness loteBusiness) {
        this.loteBusiness = loteBusiness;
    }

    @GetMapping(produces="application/json")
    public RetornoBaseModel findAll() {
        return loteBusiness.findAll();
    }

    @PostMapping(value = "/iniciar", consumes = "application/json", produces="application/json")
    public RetornoBaseModel iniciar() {
        return loteBusiness.iniciar();
    }

    @PutMapping(value = "/finalizar", consumes = "application/json", produces="application/json")
    public RetornoBaseModel finalizar() {
        return loteBusiness.finalizar();
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    public RetornoBaseModel delete(@PathVariable Long id) {
        return loteBusiness.delete(id);
    }

    @DeleteMapping(value= "/all", produces="application/json")
    public RetornoBaseModel deleteAll() {
        return loteBusiness.deleteAll();
    }

}
