package com.roberto.avitec.domain.models;

import com.roberto.avitec.domain.entities.Lote;

public class LoteAtivoModel {

    private Lote lote;
    private Boolean hasAtivo;
    private Long idadeLote;

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Boolean getHasAtivo() {
        return hasAtivo;
    }

    public void setHasAtivo(Boolean hasAtivo) {
        this.hasAtivo = hasAtivo;
    }

    public Long getIdadeLote() {
        return idadeLote;
    }

    public void setIdadeLote(Long idadeLote) {
        this.idadeLote = idadeLote;
    }
}
