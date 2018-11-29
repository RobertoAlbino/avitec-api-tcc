package com.roberto.avitec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class MonitoramentoService {

    private AjustarTemperaturaService ajustarTemperaturaService;

    @Autowired
    public MonitoramentoService(AjustarTemperaturaService ajustarTemperaturaService) {
        this.ajustarTemperaturaService = ajustarTemperaturaService;
    }

    public Integer getIluminacao() {
        return new Random().nextInt(1001);
    }

    public Integer getUmidade() {
        return new Random().nextInt(101);
    }

    public Integer getTemperatura(Long alojamento) throws Exception {
        if (ajustarTemperaturaService.getTemperatura() != null) {
            ajustarTemperaturaService.notificarUsuario(alojamento, ajustarTemperaturaService.getTemperatura());
            return ajustarTemperaturaService.getTemperatura();
        } else {
            Integer temperaturaAtual = new Random().nextInt(66) + 1;
            ajustarTemperaturaService.notificarUsuario(alojamento, ytemperaturaAtual);
            return temperaturaAtual;
        }
    }
}
