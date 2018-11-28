package com.roberto.avitec.domain.models;

import com.roberto.avitec.domain.entities.Alojamento;

import java.util.Date;

public class LoteModel {

    private Long id;
    private Date dataInicio;
    private Date dataTermino;
    private Integer quantidadeAves;
    private Alojamento alojamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Integer getQuantidadeAves() {
        return quantidadeAves;
    }

    public void setQuantidadeAves(Integer quantidadeAves) {
        this.quantidadeAves = quantidadeAves;
    }

    public Alojamento getAlojamento() {
        return alojamento;
    }

    public void setAlojamento(Alojamento alojamento) {
        this.alojamento = alojamento;
    }
}
