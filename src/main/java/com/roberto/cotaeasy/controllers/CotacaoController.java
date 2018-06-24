package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;
import com.roberto.cotaeasy.domain.models.NovaCotacaoModel;
import com.roberto.cotaeasy.service.CotacaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {
    private final Logger log = LoggerFactory.getLogger(CotacaoController.class);
    private RetornoBaseModel objetoRetorno;
    private CotacaoService cotacaoService;

    @Autowired
    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @PostMapping(value = "/novaCotacao", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel novaCotacao(@RequestBody NovaCotacaoModel novaCotacaoModel) throws Exception {
        try {
            cotacaoService.novaCotacao(novaCotacaoModel);
            return new RetornoBaseModel<NovaCotacaoModel>(true, "Cotação iniciada.", novaCotacaoModel);
        } catch (Exception ex) {
            return new RetornoBaseModel<NovaCotacaoModel>(false, ex.getMessage(), null);
        }
    }
}
