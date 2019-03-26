package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndicadorService {

    private IndicadorRepository indicadorRepository;

    @Autowired
    public IndicadorService(IndicadorRepository indicadorRepository) {
        this.indicadorRepository = indicadorRepository;
    }

    private Indicador toEntity(IndicadorModel model)  {
        Indicador indicador = new Indicador();
        indicador.setZona(model.getZona());
        indicador.setTemperatura(model.getTemperatura());
        indicador.setUmidade(model.getUmidade());
        return indicador;
    }

    public List<Indicador> findAll()  {
        return indicadorRepository.findAll();
    }

    public Indicador create(IndicadorModel model) {
        Indicador indicador = toEntity(model);
        return indicadorRepository.save(indicador);
    }
}
