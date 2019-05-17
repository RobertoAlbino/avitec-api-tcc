package com.roberto.avitec.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roberto.avitec.domain.enums.TipoEnvioPush;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "firebase")
public class Firebase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date ultimoEnvioPush;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TipoEnvioPush tipoEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUltimoEnvioPush() {
        return ultimoEnvioPush;
    }

    public void setUltimoEnvioPush(Date ultimoEnvioPush) {
        this.ultimoEnvioPush = ultimoEnvioPush;
    }

    public TipoEnvioPush getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvioPush tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }
}
