package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Lote;
import com.roberto.avitec.domain.models.LoteAtivoModel;
import com.roberto.avitec.domain.models.LoteModel;
import com.roberto.avitec.domain.models.UsuarioModel;
import com.roberto.avitec.service.LoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final Logger log = LoggerFactory.getLogger(LoteController.class);
    private LoteService LoteService;

    @Autowired
    public LoteController(LoteService LoteService) {
        this.LoteService = LoteService;
    }

    @GetMapping(value = "/all", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel findAll(@RequestBody LoteModel model) throws Exception {
        try {
            return new RetornoBaseModel<List<Lote>>(true, "Lista de Lotes", LoteService.findAll());
        } catch (Exception ex) {
            return new RetornoBaseModel<List<Lote>>(false, ex.getMessage() , null);
        }
    }

    @GetMapping(value = "/hasAtivo/{idAlojamento}", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel hasAtivo(@PathVariable Long idAlojamento) throws Exception {
        try {
            return new RetornoBaseModel<LoteAtivoModel>(true, "Lote ativo no alojamento", LoteService.hasAtivo(idAlojamento));
        } catch (Exception ex) {
            return new RetornoBaseModel<LoteAtivoModel>(false, ex.getMessage() , null);
        }
    }

    @PostMapping(value = "/criar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel criar(@RequestBody LoteModel model) throws Exception {
        try {
            Lote Lote = LoteService.criar(model);
            return new RetornoBaseModel<Lote>(true, "Lote cadastrado com sucesso.", Lote);
        } catch (Exception ex) {
            return new RetornoBaseModel<Lote>(false, ex.getMessage() , null);
        }
    }

    @PostMapping(value = "/encerrar/{id}", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel encerrar(@PathVariable Long id) throws Exception {
        try {
            LoteService.encerrar(id);
            return new RetornoBaseModel<Lote>(true, "Lote finalizado.", null);
        } catch (Exception ex) {
            return new RetornoBaseModel<Lote>(false, ex.getMessage() , null);
        }
    }
}
