package com.roberto.avitec.controller;

import com.roberto.avitec.business.LoteBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/finalizar/{id}", consumes = "application/json", produces="application/json")
    public RetornoBaseModel finalizar(@PathVariable("id") Long id) {
        return loteBusiness.finalizar(id);
    }
}
