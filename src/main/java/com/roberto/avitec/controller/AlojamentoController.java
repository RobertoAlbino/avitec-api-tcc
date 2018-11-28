package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Alojamento;
import com.roberto.avitec.domain.models.AlojamentoModel;
import com.roberto.avitec.domain.models.UsuarioModel;
import com.roberto.avitec.service.AlojamentoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alojamentos")
public class AlojamentoController {

    private final Logger log = LoggerFactory.getLogger(AlojamentoController.class);
    private AlojamentoService alojamentoService;

    @Autowired
    public AlojamentoController(AlojamentoService alojamentoService) {
        this.alojamentoService = alojamentoService;
    }

    @GetMapping(value = "/all", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel findAll() throws Exception {
        try {
            return new RetornoBaseModel<List<Alojamento>>(true, "Lista de alojamentos", alojamentoService.findAll());
        } catch (Exception ex) {
            return new RetornoBaseModel<List<Alojamento>>(false, ex.getMessage() , null);
        }
    }

    @PostMapping(value = "/criar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel criar(@RequestBody AlojamentoModel model) throws Exception {
        try {
            Alojamento alojamento = alojamentoService.criar(model);
            return new RetornoBaseModel<Alojamento>(true, "Alojamento cadastrado com sucesso.", alojamento);
        } catch (Exception ex) {
            return new RetornoBaseModel<Alojamento>(false, ex.getMessage() , null);
        }
    }
}
