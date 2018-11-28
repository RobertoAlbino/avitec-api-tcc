package com.roberto.avitec.domain.enums;

public enum ETipoEmail {

    ANOMALIA(0);

    private int tipo;

    ETipoEmail(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
