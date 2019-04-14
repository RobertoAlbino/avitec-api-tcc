package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Lote;
import com.roberto.avitec.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            throw new RuntimeException("Nenhum lote ativo foi encontrado");
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

    public RetornoBaseModel finalizar() {
        try {
            Lote lote = loteService.getLoteAtivo();
            validateFinalizacaoLote(lote);
            return new RetornoBaseModel(true, "Lote finalizado com sucesso", loteService.finalizar(lote));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel delete(Long id) {
        try {
            loteService.delete(id);
            return new RetornoBaseModel(true, "Lote excluído com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel deleteAll() {
        try {
            List<Lote> lotes = loteService.findAll();
            for (Lote lote: lotes) {
                delete(lote.getId());
            }
            return new RetornoBaseModel(true, "Todos os lotes foram excluídos com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
