package com.roberto.avitec.domain.models;

import java.math.BigDecimal;

public class MediaUltimosIndicadoresModel {

    private BigDecimal mediaTemperatura;
    private BigDecimal mediaUmidade;

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
}
