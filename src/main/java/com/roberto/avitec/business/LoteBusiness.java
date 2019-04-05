package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Lote;
import com.roberto.avitec.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoteBusiness {

    private LoteService loteService;

    @Autowired
    public LoteBusiness(LoteService loteService) {
        this.loteService = loteService;
    }

    private void validateInicioLote() {
        if (loteService.hasLoteAtivo()) {
            throw new RuntimeException("Já existe outro lote em andamento");
        }
    }

    private void validateFinalizacaoLote(Lote lote) {
        if (lote == null) {
            throw new RuntimeException("Lote não encontrado");
        }
        if (!lote.getAtivo()) {
            throw new RuntimeException("Esse lote ainda não foi iniciado");
        }
    }

    public RetornoBaseModel findAll() {
        try {
            return new RetornoBaseModel(true, "Lista de lotes", loteService.findAll());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel iniciar() {
        try {
            validateInicioLote();
            return new RetornoBaseModel(true, "Lote criado com sucesso", loteService.iniciar());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel finalizar(Long id) {
        try {
            Lote lote = loteService.find(id);
            validateFinalizacaoLote(lote);
            return new RetornoBaseModel(true, "Lote finalizado com sucesso", loteService.finalizar(lote));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
