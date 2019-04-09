package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Lote;
import com.roberto.avitec.repository.LoteRepository;
import com.roberto.avitec.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class LoteService {

    private LoteRepository loteRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote find(Long id)  {
        return loteRepository.findOne(id);
    }

    public List<Lote> findAll()  {
        return loteRepository.findAll();
    }

    public Lote iniciar() {
        Lote lote = new Lote();
        lote.setId(0L);
        lote.setDataInicio(DateUtils.now());
        lote.setAtivo(true);
        return loteRepository.save(lote);
    }

    public Lote finalizar(Lote lote) {
        lote.setDataEncerramento(DateUtils.now());
        lote.setAtivo(false);
        return loteRepository.save(lote);
    }

    public Lote getLoteAtivo() {
        return loteRepository.getFirstByAtivo(true);
    }

    public boolean hasLoteAtivo() {
        return loteRepository.existsLoteByAtivoTrue();
    }
}
