package com.roberto.avitec.domain.enums;

public enum ETipoEmail {
    NOVA_COTACAO_INICIADA(0),
    NOVO_LANCE_RECEBIDO(1);

    private int tipo;

    ETipoEmail(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
