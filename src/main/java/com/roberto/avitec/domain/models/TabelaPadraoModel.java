package com.roberto.avitec.domain.models;

import java.math.BigDecimal;

public class TabelaPadraoModel {

    private Long id;

    private Integer dias;

    private BigDecimal temperaturaMinima;

    private BigDecimal temperaturaMaxima;

    private BigDecimal umidadeMinima;

    private BigDecimal umidadeMaxima;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public BigDecimal getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(BigDecimal temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public BigDecimal getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(BigDecimal temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public BigDecimal getUmidadeMinima() {
        return umidadeMinima;
    }

    public void setUmidadeMinima(BigDecimal umidadeMinima) {
        this.umidadeMinima = umidadeMinima;
    }

    public BigDecimal getUmidadeMaxima() {
        return umidadeMaxima;
    }

    public void setUmidadeMaxima(BigDecimal umidadeMaxima) {
        this.umidadeMaxima = umidadeMaxima;
    }
}
