package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;

import com.roberto.cotaeasy.domain.entities.CotacaoLance;
import com.roberto.cotaeasy.domain.models.BuscarLancesModel;
import com.roberto.cotaeasy.domain.models.NovoLanceModel;
import com.roberto.cotaeasy.service.LanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/lances")
public class LanceController {
    private final Logger log = LoggerFactory.getLogger(LanceController.class);
    private RetornoBaseModel objetoRetorno;
    private LanceService lanceService;

    @Autowired
    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }

    @PostMapping(value = "/novoLance", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel novoLance(@RequestBody NovoLanceModel novoLanceModel) throws Exception {
        try {
            lanceService.novoLance(novoLanceModel);
            return new RetornoBaseModel<Boolean>(true, "Lance criado com sucesso.", true);
        } catch (Exception ex) {
            return new RetornoBaseModel<Boolean>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/getTodosLanceProduto", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel getTodosLanceProduto(@RequestBody BuscarLancesModel buscarLancesModel) throws Exception {
        try {
            LinkedList<CotacaoLance> lances = lanceService.getTodosLanceProduto(buscarLancesModel);
            return new RetornoBaseModel<LinkedList<CotacaoLance>>(true, "Lance criado com sucesso.", lances);
        } catch (Exception ex) {
            return new RetornoBaseModel<LinkedList<CotacaoLance>>(false, ex.getMessage(), null);
        }
    }
}
