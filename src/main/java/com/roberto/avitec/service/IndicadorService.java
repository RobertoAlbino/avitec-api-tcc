package com.roberto.avitec.service;

import com.roberto.avitec.business.LoteBusiness;
import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.models.ExtremosIndicadoresPorDiaModel;
import com.roberto.avitec.repository.IndicadorRepository;
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


    public List<Indicador> findAll()  {
        return indicadorRepository.findAll();
    }

    public List<Indicador> getUltimos()  {
        return indicadorRepository.getUltimosIndicadoresPorZona();
    }

    public Object[] informacoesFechamentoLote(Long idLote) {
        return indicadorRepository.extremosIndicadoresPorDiaLote(idLote);
    }

    public List<Indicador> getByZona(Integer zona)  {
        return indicadorRepository.findByZona(zona);
    }

    public Indicador create(Indicador indicador) {
        return indicadorRepository.save(indicador);
    }

    public void delete(Long id) {
        indicadorRepository.delete(id);
    }
}
