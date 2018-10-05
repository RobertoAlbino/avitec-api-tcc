package com.roberto.avitec.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "historico_humidade")
public class HistoricoHumidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date data;

    @NotNull
    private Double valor;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Alojamento getAlojamento() {
        return alojamento;
    }

    public void setAlojamento(Alojamento alojamento) {
        this.alojamento = alojamento;
    }
}
