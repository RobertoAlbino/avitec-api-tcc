package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.service.IndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndicadorBusiness {

    private IndicadorService indicadorService;

    @Autowired
    public IndicadorBusiness(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    public RetornoBaseModel findAll() {
        try {
            return new RetornoBaseModel(true, "Lista de indicadores", indicadorService.findAll());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel getUltimos() {
        try {
            return new RetornoBaseModel(true, "Lista dos ultímos indicadores", indicadorService.getUltimos());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel create(IndicadorModel model) {
        try {
            return new RetornoBaseModel(true, "Indicador criado com sucesso", indicadorService.create(model));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel delete(Long id) {
        try {
            indicadorService.delete(id);
            return new RetornoBaseModel(true, "Indicador excluído com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel deleteAll() {
        try {
            List<Indicador> indicadores = indicadorService.findAll();
            for (Indicador indicador: indicadores) {
                delete(indicador.getId());
            }
            return new RetornoBaseModel(true, "Todos os indicadores foram excluídos com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
