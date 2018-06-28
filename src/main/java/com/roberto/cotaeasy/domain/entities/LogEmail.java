package com.roberto.cotaeasy.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roberto.cotaeasy.domain.enums.ETipoEmail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "log_email")
public class LogEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String destino;

    @NotNull
    private String assunto;

    @NotNull
    private String corpo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataEnvio;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private ETipoEmail tipoEmail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public ETipoEmail getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(ETipoEmail tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Destino: " + destino + " Assunto: " + assunto;
    }
}
