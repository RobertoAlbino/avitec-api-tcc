package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Alojamento;
import com.roberto.avitec.domain.models.AlojamentoModel;
import com.roberto.avitec.repository.AlojamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlojamentoService {

    private final Logger log = LoggerFactory.getLogger(AlojamentoService.class);
    private AlojamentoRepository alojamentoRepository;

    @Autowired
    public AlojamentoService(AlojamentoRepository alojamentoRepository) {
        this.alojamentoRepository = alojamentoRepository;
    }

    private Alojamento modelToEntity(AlojamentoModel model) {
        Alojamento alojamento = new Alojamento();
        alojamento.setId(model.getId());
        alojamento.setDescricao(model.getDescricao());
        return alojamento;
    }

    public Alojamento find(Long idAlojamento) {
        return alojamentoRepository.findOne(idAlojamento);
    }

    public List<Alojamento> findAll()  {
        return alojamentoRepository.findAll();
    }

    public Alojamento criar(AlojamentoModel model) throws Exception {
        Alojamento alojamento = modelToEntity(model);
        return alojamentoRepository.save(alojamento);
    }
}
