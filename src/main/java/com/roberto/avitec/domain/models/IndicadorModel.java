package com.roberto.avitec.domain.models;

import java.math.BigDecimal;

public class IndicadorModel {

    private Integer zona;
    private BigDecimal temperatura;
    private BigDecimal umidade;

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
}
