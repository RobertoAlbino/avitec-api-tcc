package com.roberto.avitec.controller;

import com.roberto.avitec.business.IndicadorBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indicadores")
public class IndicadorController {

    private IndicadorBusiness indicadorBusiness;

    @Autowired
    public IndicadorController(IndicadorBusiness indicadorBusiness) {
        this.indicadorBusiness = indicadorBusiness;
    }

    @PostMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel create(@RequestBody IndicadorModel indicador) {
        return indicadorBusiness.create(indicador);
    }
}
