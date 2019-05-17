package com.roberto.avitec.domain.enums;

public enum TipoEnvioPush {

    TEMPERATURA(0),
    UMIDADE(1);

    public int tipo;

    TipoEnvioPush(int valor) {
        tipo = valor;
    }
}
