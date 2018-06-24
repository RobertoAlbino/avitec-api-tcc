package com.roberto.cotaeasy.domain.models;

import com.roberto.cotaeasy.domain.entities.Cotacao;
import com.roberto.cotaeasy.domain.entities.CotacaoFornecedor;

import java.util.LinkedList;

public class NovaCotacaoModel {
    private Cotacao cotacao;
    private LinkedList<CotacaoFornecedor> fornecedores;

    public NovaCotacaoModel() {}

    public NovaCotacaoModel(Cotacao cotacao, LinkedList<CotacaoFornecedor> fornecedores) {
        this.cotacao = cotacao;
        this.fornecedores = fornecedores;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }

    public LinkedList<CotacaoFornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(LinkedList<CotacaoFornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
