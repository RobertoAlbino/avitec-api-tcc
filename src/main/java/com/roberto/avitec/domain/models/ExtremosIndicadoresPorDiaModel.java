package com.roberto.avitec.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ExtremosIndicadoresPorDiaModel {

    private BigDecimal temperaturaMaxima;
    private BigDecimal temperaturaMinima;
    private BigDecimal umidadeMaxima;
    private BigDecimal umidadeMinima;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
    private Date data;

    public BigDecimal getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(BigDecimal temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public BigDecimal getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(BigDecimal temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public BigDecimal getUmidadeMaxima() {
        return umidadeMaxima;
    }

    public void setUmidadeMaxima(BigDecimal umidadeMaxima) {
        this.umidadeMaxima = umidadeMaxima;
    }

    public BigDecimal getUmidadeMinima() {
        return umidadeMinima;
    }

    public void setUmidadeMinima(BigDecimal umidadeMinima) {
        this.umidadeMinima = umidadeMinima;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
