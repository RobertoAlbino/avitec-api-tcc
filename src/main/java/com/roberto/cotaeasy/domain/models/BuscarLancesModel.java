package com.roberto.cotaeasy.domain.models;

public class BuscarLancesModel {
    private int idUsuario;
    private int idProduto;

    public BuscarLancesModel() {}

    public BuscarLancesModel(int idUsuario, int idProduto) {
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
}
