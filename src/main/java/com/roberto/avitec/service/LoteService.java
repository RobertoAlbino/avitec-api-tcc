package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Lote;
import com.roberto.avitec.domain.models.LoteAtivoModel;
import com.roberto.avitec.domain.models.LoteModel;
import com.roberto.avitec.repository.LoteRepository;
import com.roberto.avitec.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class LoteService {

    private final Logger log = LoggerFactory.getLogger(LoteService.class);
    private LoteRepository loteRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    private Lote modelToEntity(LoteModel model) {
        Lote lote = new Lote();
        lote.setId(model.getId());
        lote.setDataInicio(model.getDataInicio());
        lote.setDataTermino(model.getDataTermino());
        lote.setQuantidadeAves(model.getQuantidadeAves());
        lote.setAlojamento(model.getAlojamento());
        return lote;
    }

    private Long calcularIdadeLote(Lote lote) {
        return Duration.between(lote.getDataInicio().toInstant(), Instant.now()).toDays() + 1;
    }

    public List<Lote> findAll()  {
        return loteRepository.findAll();
    }

    public Lote criar(LoteModel model) throws Exception {
        Lote lote = modelToEntity(model);
        lote.setDataInicio(DateUtils.now());
        return loteRepository.save(lote);
    }

    public LoteAtivoModel hasAtivo(Long idAlojamento) {
        LoteAtivoModel loteAtivo = new LoteAtivoModel();
        Lote lote = loteRepository.findByAlojamento_IdAndDataTerminoIsNull(idAlojamento);
        if (Objects.nonNull(lote)) {
            loteAtivo.setHasAtivo(true);
            loteAtivo.setIdadeLote(calcularIdadeLote(lote));
            loteAtivo.setLote(lote);
        } else {
            loteAtivo.setHasAtivo(false);
            loteAtivo.setIdadeLote(0L);
            loteAtivo.setLote(null);
        }
        return loteAtivo;
    }

    public void encerrar(Long id) {
        Lote lote = loteRepository.findOne(id);
        lote.setDataTermino(DateUtils.now());
        loteRepository.save(lote);
    }
}
