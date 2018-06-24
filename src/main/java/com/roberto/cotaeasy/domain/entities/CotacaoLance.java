package com.roberto.cotaeasy.domain.entities;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "cotacoes_lances")
public class CotacaoLance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Double lance;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_cotacao")
    private Cotacao cotacao;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_fornecedor")
    private Usuario fornecedor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLance() {
        return lance;
    }

    public void setLance(Double lance) {
        this.lance = lance;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }

    public Usuario getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Usuario fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Cotacao: " + id;
    }
}
