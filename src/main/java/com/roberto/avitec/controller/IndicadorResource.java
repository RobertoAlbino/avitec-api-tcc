package com.roberto.avitec.controller;

import com.roberto.avitec.business.IndicadorBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedList;
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

    @PostMapping(value = "/raw", consumes = MediaType.TEXT_PLAIN_VALUE, produces="application/json")
    public List<RetornoBaseModel> createRaw(@RequestBody String raw) {
        raw = raw.replace("\\","");
        raw = raw.replace("/","");
        raw = raw.replace("\"","");
        List<RetornoBaseModel> retorno = new LinkedList<>();
        String[] indicadores = raw.split(";");
        if (indicadores == null) {
            retorno.add(new RetornoBaseModel(false, "Nenhum indicador informado", null));
            return retorno;
        }
        for (String indicadorArray : indicadores) {
            String[] indicador = indicadorArray.split(",");
            IndicadorModel model = new IndicadorModel();
            model.setZona(new Integer(indicador[0]));
            model.setTemperatura(new BigDecimal(indicador[1]));
            model.setUmidade(new BigDecimal(indicador[2]));
            retorno.add(indicadorBusiness.create(model));
        }
        return retorno;
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
