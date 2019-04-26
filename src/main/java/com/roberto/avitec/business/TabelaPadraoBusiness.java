package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.TabelaPadrao;
import com.roberto.avitec.domain.models.TabelaPadraoModel;
import com.roberto.avitec.service.TabelaPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TabelaPadraoBusiness {

    private TabelaPadraoService tabelaPadraoService;

    @Autowired
    public TabelaPadraoBusiness(TabelaPadraoService tabelaPadraoService) {
        this.tabelaPadraoService = tabelaPadraoService;
    }

    private TabelaPadrao toEntity(TabelaPadraoModel tabelaPadraoModel, boolean isUpdate) {
        TabelaPadrao tabelaPadrao = new TabelaPadrao();
        if (isUpdate) {
            if (tabelaPadraoModel.getId() == null) {
                throw new RuntimeException("O identificador do registro precisa ser informado em uma atualização");
            }
            tabelaPadrao.setId(tabelaPadraoModel.getId());
        }
        tabelaPadrao.setDias(tabelaPadraoModel.getDias());
        tabelaPadrao.setTemperaturaMinima(tabelaPadraoModel.getTemperaturaMinima());
        tabelaPadrao.setTemperaturaMaxima(tabelaPadraoModel.getTemperaturaMaxima());
        tabelaPadrao.setUmidadeMinima(tabelaPadraoModel.getUmidadeMinima());
        tabelaPadrao.setUmidadeMaxima(tabelaPadraoModel.getUmidadeMaxima());
        return tabelaPadrao;
    }

    private void validateQuantidadeLinhasTabelaPadrao() {
        if (tabelaPadraoService.findAll().size() > 6) {
            throw new RuntimeException("A tabela padrão já possuí sete linhas de informação");
        }
    }

    public RetornoBaseModel findAll() {
        try {
            return new RetornoBaseModel(true, "Tabela padrão", tabelaPadraoService.findAll());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel create(TabelaPadraoModel tabelaPadraoModel) {
        try {
            validateQuantidadeLinhasTabelaPadrao();
            TabelaPadrao tabelaPadrao = toEntity(tabelaPadraoModel, false);
            return new RetornoBaseModel(true, "Registro da tabela padrão criado com sucesso", tabelaPadraoService.save(tabelaPadrao));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel update(TabelaPadraoModel tabelaPadraoModel) {
        try {
            TabelaPadrao tabelaPadrao = toEntity(tabelaPadraoModel, true);
            return new RetornoBaseModel(true, "Registro da tabela padrão atualizado com sucesso", tabelaPadraoService.save(tabelaPadrao));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel delete(Long id) {
        try {
            tabelaPadraoService.delete(id);
            return new RetornoBaseModel(true, "Registro da tabela padrão removido com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
