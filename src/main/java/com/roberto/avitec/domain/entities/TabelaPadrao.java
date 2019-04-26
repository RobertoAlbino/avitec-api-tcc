package com.roberto.avitec.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "tabela_padrao")
public class TabelaPadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer dias;

    @NotNull
    private BigDecimal temperaturaMinima;

    @NotNull
    private BigDecimal temperaturaMaxima;

    @NotNull
    private BigDecimal umidadeMinima;

    @NotNull
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
