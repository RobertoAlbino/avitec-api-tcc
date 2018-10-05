package com.roberto.avitec.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date dataInicio;

    @NotNull
    private Date dataTermino;

    @NotNull
    private Integer quantidadeAves;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_alojamento")
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
