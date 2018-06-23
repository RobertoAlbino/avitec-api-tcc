package com.roberto.cotaeasy.domain.enums;

public enum EPerfil {
    USUARIO(0),
    FORNECEDOR(1);

    private int perfil;

    EPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getPerfil() {
        return perfil;
    }
}
