package com.roberto.avitec.controller;

import com.roberto.avitec.business.IndicadorBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel create(@RequestBody IndicadorModel indicador) {
        return indicadorBusiness.create(indicador);
    }
}
