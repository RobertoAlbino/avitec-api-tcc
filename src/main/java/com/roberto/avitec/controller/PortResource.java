package com.roberto.avitec.controller;

import com.roberto.avitec.AviTecApp;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/port")
public class PortResource {

    @GetMapping(produces="application/json")
    public RetornoBaseModel getPort() {
        return new RetornoBaseModel(true, "Porta da aplicação", AviTecApp.getPort());
    }
}
