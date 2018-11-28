package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Equipamento;
import com.roberto.avitec.domain.models.EquipamentoModel;
import com.roberto.avitec.repository.EquipamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipamentoService {

    private final Logger log = LoggerFactory.getLogger(EquipamentoService.class);
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    private Equipamento modelToEntity(EquipamentoModel model) {
        Equipamento equipamento = new Equipamento();
        equipamento.setId(model.getId());
        equipamento.setDescricao(model.getDescricao());
        equipamento.setAlojamento(model.getAlojamento());
        return equipamento;
    }

    public List<Equipamento> findAll()  {
        return equipamentoRepository.findAll();
    }

    public Equipamento criar(EquipamentoModel model) throws Exception {
        Equipamento Equipamento = modelToEntity(model);
        return equipamentoRepository.save(Equipamento);
    }
}
