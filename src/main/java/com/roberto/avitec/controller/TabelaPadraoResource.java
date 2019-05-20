package com.roberto.avitec.controller;

import com.roberto.avitec.business.TabelaPadraoBusiness;
import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.TabelaPadraoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tabelapadrao")
public class TabelaPadraoResource {

    private TabelaPadraoBusiness tabelaPadraoBusiness;

    @Autowired
    public TabelaPadraoResource(TabelaPadraoBusiness tabelaPadraoBusiness) {
        this.tabelaPadraoBusiness = tabelaPadraoBusiness;
    }

    @GetMapping(produces="application/json")
    public RetornoBaseModel findAll() {
        return tabelaPadraoBusiness.findAll();
    }

    @PostMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel create(@RequestBody TabelaPadraoModel tabelaPadraoModel) {
        return tabelaPadraoBusiness.create(tabelaPadraoModel);
    }

    @PutMapping(consumes = "application/json", produces="application/json")
    public RetornoBaseModel update(@RequestBody TabelaPadraoModel tabelaPadraoModel) {
        return tabelaPadraoBusiness.update(tabelaPadraoModel);
    }

    @PutMapping(value= "/reset", consumes = "application/json", produces="application/json")
    public RetornoBaseModel reset() {
        return tabelaPadraoBusiness.reset();
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    public RetornoBaseModel delete(@PathVariable Long id) {
        return tabelaPadraoBusiness.delete(id);
    }
}
