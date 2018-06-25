package com.roberto.cotaeasy.domain.models;

import com.roberto.cotaeasy.domain.entities.Cotacao;

public class NovoLanceModel {
    private double lance;
    private long idFornecedor;
    private Cotacao cotacao;

    public NovoLanceModel() {}

    public NovoLanceModel(double lance, long idFornecedor, Cotacao cotacao) {
        this.lance = lance;
        this.idFornecedor = idFornecedor;
        this.cotacao = cotacao;
    }

    public double getLance() {
        return lance;
    }

    public void setLance(double lance) {
        this.lance = lance;
    }

    public long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }
}
