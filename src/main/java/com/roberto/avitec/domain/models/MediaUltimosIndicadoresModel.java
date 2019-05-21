package com.roberto.avitec.domain.models;

import java.math.BigDecimal;

public class MediaUltimosIndicadoresModel {

    private BigDecimal mediaTemperatura;
    private Boolean isTemperaturaIdeal;
    private BigDecimal mediaUmidade;
    private Boolean isUmidadeIdeal;

    public BigDecimal getMediaTemperatura() {
        return mediaTemperatura;
    }

    public void setMediaTemperatura(BigDecimal mediaTemperatura) {
        this.mediaTemperatura = mediaTemperatura;
    }

    public BigDecimal getMediaUmidade() {
        return mediaUmidade;
    }

    public void setMediaUmidade(BigDecimal mediaUmidade) {
        this.mediaUmidade = mediaUmidade;
    }

    public Boolean getTemperaturaIdeal() {
        return isTemperaturaIdeal;
    }

    public void setTemperaturaIdeal(Boolean temperaturaIdeal) {
        isTemperaturaIdeal = temperaturaIdeal;
    }

    public Boolean getUmidadeIdeal() {
        return isUmidadeIdeal;
    }

    public void setUmidadeIdeal(Boolean umidadeIdeal) {
        isUmidadeIdeal = umidadeIdeal;
    }
}
