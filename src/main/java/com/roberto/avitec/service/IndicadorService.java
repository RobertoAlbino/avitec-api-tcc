package com.roberto.avitec.service;

import com.roberto.avitec.business.LoteBusiness;
import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.repository.IndicadorRepository;
import com.roberto.avitec.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class IndicadorService {

    private LoteBusiness loteBusiness;
    private IndicadorRepository indicadorRepository;

    @Autowired
    public IndicadorService(IndicadorRepository indicadorRepository,
                            LoteBusiness loteBusiness) {
        this.indicadorRepository = indicadorRepository;
        this.loteBusiness = loteBusiness;
    }

    private Indicador toEntity(IndicadorModel model)  {
        Indicador indicador = new Indicador();
        indicador.setZona(model.getZona());
        indicador.setTemperatura(model.getTemperatura());
        indicador.setUmidade(model.getUmidade());
        indicador.setData(DateUtils.now());
        indicador.setLote(loteBusiness.getLoteAtivo());
        return indicador;
    }

    public List<Indicador> findAll()  {
        return indicadorRepository.findAll();
    }

    public List<Indicador> getUltimos()  {
        return indicadorRepository.getUltimosIndicadoresPorZona();
    }

    public List<Indicador> getByZona(Integer zona)  {
        return indicadorRepository.findByZona(zona);
    }

    public Indicador create(IndicadorModel model) {
        Indicador indicador = toEntity(model);
        return indicadorRepository.save(indicador);
    }

    public void delete(Long id) {
        indicadorRepository.delete(id);
    }
}
