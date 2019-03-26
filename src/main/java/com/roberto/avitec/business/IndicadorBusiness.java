package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.service.IndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndicadorBusiness {

    private IndicadorService indicadorService;

    @Autowired
    public IndicadorBusiness(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    public RetornoBaseModel create(IndicadorModel model) {
        try {
            return new RetornoBaseModel(true, "Indicador criado com sucesso", indicadorService.create(model));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
