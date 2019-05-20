package com.roberto.avitec.controller;

import com.roberto.avitec.business.IndicadorBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
public class IndicadorResource {

    private IndicadorBusiness indicadorBusiness;

    @Autowired
    public IndicadorResource(IndicadorBusiness indicadorBusiness) {
        this.indicadorBusiness = indicadorBusiness;
    }

    @GetMapping(produces="application/json")
    public RetornoBaseModel findAll() {
        return indicadorBusiness.findAll();
    }

    @GetMapping(value = "/ultimos", produces="application/json")
    public RetornoBaseModel getUltimos() {
        return indicadorBusiness.getUltimos();
    }

    @GetMapping(value = "/zona/{zona}", produces="application/json")
    public RetornoBaseModel getByZona(@PathVariable Integer zona) {
        return indicadorBusiness.getByZona(zona);
    }

    @GetMapping(value = "/media", produces="application/json")
    public RetornoBaseModel mediaUltimosIndicadores() {
        return indicadorBusiness.mediaUltimosIndicadores();
    }

    @PostMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel create(@RequestBody IndicadorModel indicador) {
        return indicadorBusiness.create(indicador);
    }

    @PostMapping(value = "/list", consumes = "application/json", produces="application/json")
    public RetornoBaseModel createList(@RequestBody List<IndicadorModel> indicadores) {
        return indicadorBusiness.createList(indicadores);
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    public RetornoBaseModel delete(@PathVariable Long id) {
        return indicadorBusiness.delete(id);
    }

    @DeleteMapping(value= "/all", produces="application/json")
    public RetornoBaseModel deleteAll() {
        return indicadorBusiness.deleteAll();
    }
}
