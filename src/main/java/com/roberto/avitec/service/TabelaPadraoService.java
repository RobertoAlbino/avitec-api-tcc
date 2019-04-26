package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.TabelaPadrao;
import com.roberto.avitec.repository.TabelaPadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TabelaPadraoService {

    private TabelaPadraoRepository tabelaPadraoRepository;

    @Autowired
    public TabelaPadraoService(TabelaPadraoRepository tabelaPadraoRepository) {
        this.tabelaPadraoRepository = tabelaPadraoRepository;
    }

    public List<TabelaPadrao> findAll()  {
        return tabelaPadraoRepository.findAll();
    }

    public TabelaPadrao save(TabelaPadrao tabelaPadrao) {
        return tabelaPadraoRepository.save(tabelaPadrao);
    }

    public void delete(Long id) {
        tabelaPadraoRepository.delete(id);
    }
}