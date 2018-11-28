package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;

import com.roberto.avitec.service.AjustarTemperaturaService;
import com.roberto.avitec.service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monitoramento")
public class MonitoramentoController {

    private MonitoramentoService monitoramentoService;
    private AjustarTemperaturaService ajustarTemperaturaService;

    @Autowired
    public MonitoramentoController(MonitoramentoService monitoramentoService,
                                   AjustarTemperaturaService ajustarTemperaturaService) {
        this.monitoramentoService = monitoramentoService;
        this.ajustarTemperaturaService = ajustarTemperaturaService;
    }

    @GetMapping(value = "/iluminacao",produces="application/json")
    public RetornoBaseModel getIluminacao() throws Exception {
        try {
            return new RetornoBaseModel<Integer>(true, "Iluminação do ambiente", monitoramentoService.getIluminacao());
        } catch (Exception ex) {
            return new RetornoBaseModel<Integer>(false, ex.getMessage() , null);
        }
    }

    @GetMapping(value = "/umidade",produces="application/json")
    public RetornoBaseModel getUmidade() throws Exception {
        try {
            return new RetornoBaseModel<Integer>(true, "Umidade relativa do ar", monitoramentoService.getUmidade());
        } catch (Exception ex) {
            return new RetornoBaseModel<Integer>(false, ex.getMessage() , null);
        }
    }

    @GetMapping(value = "/temperatura",produces="application/json")
    public RetornoBaseModel getTemperatura() throws Exception {
        try {
            return new RetornoBaseModel<Integer>(true, "Temperatura do ambiente", monitoramentoService.getTemperatura());
        } catch (Exception ex) {
            return new RetornoBaseModel<Integer>(false, ex.getMessage() , null);
        }
    }

    @PostMapping(value = "/temperatura/ajustar/{alojamento}/temperatura/{temperatura}", produces="application/json")
    public RetornoBaseModel ajustarTemperatura(@PathVariable Long alojamento, @PathVariable Integer temperatura) throws Exception {
        try {
            return new RetornoBaseModel<Integer>(true, "Temperatura ajustada", ajustarTemperaturaService.setTemperatura(alojamento, temperatura));
        } catch (Exception ex) {
            return new RetornoBaseModel<Integer>(false, ex.getMessage() , null);
        }
    }


}
