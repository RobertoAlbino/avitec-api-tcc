package com.roberto.avitec.controller;

import com.roberto.avitec.business.IndicadorBusiness;
import com.roberto.avitec.business.LoteBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotes")
public class LoteResource {

    private LoteBusiness loteBusiness;
    private IndicadorBusiness indicadorBusiness;

    @Autowired
    public LoteResource(LoteBusiness loteBusiness, IndicadorBusiness indicadorBusiness) {
        this.loteBusiness = loteBusiness;
        this.indicadorBusiness = indicadorBusiness;
    }

    @GetMapping(produces="application/json")
    public RetornoBaseModel findAll() {
        return loteBusiness.findAll();
    }

    @GetMapping(value = "{id}/finalizar/info", produces="application/json")
    public RetornoBaseModel infoFinalizacaoLote(@PathVariable Long id) {
        return indicadorBusiness.informacoesFechamentoLote(id);
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
