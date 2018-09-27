package com.roberto.avitec.domain.base;

public class RetornoBaseModel<T> {
    private boolean sucesso;
    private String mensagem;
    private T objeto;

    public RetornoBaseModel(Boolean sucesso, String mensagem, T objeto) {
        setSucesso(sucesso);
        setMensagem(mensagem);
        setObjeto(objeto);
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return  "Sucesso: "  + isSucesso() +
                "Mensagem: " + getMensagem() +
                "Objeto: "   + getObjeto();
    }
}
