package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Equipamento;
import com.roberto.avitec.domain.models.EquipamentoModel;
import com.roberto.avitec.domain.models.UsuarioModel;
import com.roberto.avitec.service.EquipamentoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final Logger log = LoggerFactory.getLogger(EquipamentoController.class);
    private EquipamentoService equipamentoService;

    @Autowired
    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping(value = "/all", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel findAll(@RequestBody EquipamentoModel model) throws Exception {
        try {
            return new RetornoBaseModel<List<Equipamento>>(true, "Lista de Equipamentos", equipamentoService.findAll());
        } catch (Exception ex) {
            return new RetornoBaseModel<List<Equipamento>>(false, ex.getMessage() , null);
        }
    }

    @PostMapping(value = "/criar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel criar(@RequestBody EquipamentoModel model) throws Exception {
        try {
            Equipamento equipamento = equipamentoService.criar(model);
            return new RetornoBaseModel<Equipamento>(true, "Equipamento cadastrado com sucesso.", equipamento);
        } catch (Exception ex) {
            return new RetornoBaseModel<Equipamento>(false, ex.getMessage() , null);
        }
    }
}
