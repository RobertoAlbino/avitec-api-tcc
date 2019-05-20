package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.TabelaPadrao;
import com.roberto.avitec.domain.models.TabelaPadraoModel;
import com.roberto.avitec.service.TabelaPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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

    public RetornoBaseModel reset() {
        try {
            List<TabelaPadrao> tabela = tabelaPadraoService.findAll();
            if (tabela.size() != 7) {
                return new RetornoBaseModel(true, "A tabela precisa possuir um total de sete semanas", tabela );
            }
            tabela.get(0).setDias(0);
            tabela.get(0).setTemperaturaMaxima(new BigDecimal(35));
            tabela.get(0).setTemperaturaMinima(new BigDecimal(32));
            tabela.get(0).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(0).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(0));
            tabela.get(1).setDias(8);
            tabela.get(1).setTemperaturaMaxima(new BigDecimal(32));
            tabela.get(1).setTemperaturaMinima(new BigDecimal(29));
            tabela.get(1).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(1).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(1));
            tabela.get(2).setDias(15);
            tabela.get(2).setTemperaturaMaxima(new BigDecimal(29));
            tabela.get(2).setTemperaturaMinima(new BigDecimal(26));
            tabela.get(2).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(2).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(2));
            tabela.get(3).setDias(22);
            tabela.get(3).setTemperaturaMaxima(new BigDecimal(26));
            tabela.get(3).setTemperaturaMinima(new BigDecimal(23));
            tabela.get(3).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(3).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(3));
            tabela.get(4).setDias(29);
            tabela.get(4).setTemperaturaMaxima(new BigDecimal(23));
            tabela.get(4).setTemperaturaMinima(new BigDecimal(20));
            tabela.get(4).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(4).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(4));
            tabela.get(5).setDias(36);
            tabela.get(5).setTemperaturaMaxima(new BigDecimal(20));
            tabela.get(5).setTemperaturaMinima(new BigDecimal(20));
            tabela.get(5).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(5).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(5));
            tabela.get(6).setDias(43);
            tabela.get(6).setTemperaturaMaxima(new BigDecimal(20));
            tabela.get(6).setTemperaturaMinima(new BigDecimal(20));
            tabela.get(6).setUmidadeMaxima(new BigDecimal(70));
            tabela.get(6).setUmidadeMinima(new BigDecimal(60));
            tabelaPadraoService.save(tabela.get(6));
            return new RetornoBaseModel(true, "Tabela padrão resetada com sucesso", tabelaPadraoService.findAll());
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
