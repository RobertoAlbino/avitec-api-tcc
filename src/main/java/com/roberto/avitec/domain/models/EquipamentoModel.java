package com.roberto.avitec.domain.models;

import com.roberto.avitec.domain.entities.Alojamento;

public class EquipamentoModel {

    public Long id;
    public String descricao;
    public Alojamento alojamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Alojamento getAlojamento() {
        return alojamento;
    }

    public void setAlojamento(Alojamento alojamento) {
        this.alojamento = alojamento;
    }
}
