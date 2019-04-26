package com.roberto.avitec.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "indicador")
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer zona;

    @NotNull
    private BigDecimal temperatura;

    @NotNull
    private BigDecimal umidade;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date data;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;

    @NotNull
    private Boolean isTemperaturaIdeal;

    @NotNull
    private Boolean isUmidadeIdeal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZona() {
        return zona;
    }

    public void setZona(Integer zona) {
        this.zona = zona;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public BigDecimal getUmidade() {
        return umidade;
    }

    public void setUmidade(BigDecimal umidade) {
        this.umidade = umidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Boolean isTemperaturaIdeal() {
        return isTemperaturaIdeal;
    }

    public void setTemperaturaIdeal(Boolean temperaturaIdeal) {
        this.isTemperaturaIdeal = temperaturaIdeal;
    }

    public Boolean isUmidadeIdeal() {
        return isUmidadeIdeal;
    }

    public void setUmidadeIdeal(Boolean umidadeIdeal) {
        this.isUmidadeIdeal = umidadeIdeal;
    }
}
