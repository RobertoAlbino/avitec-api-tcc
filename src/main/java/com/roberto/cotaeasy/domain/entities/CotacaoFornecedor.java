package com.roberto.cotaeasy.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "cotacoes_fornecedores")
public class CotacaoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
        return "CotacaoFornecedor: " + id;
    }
}
