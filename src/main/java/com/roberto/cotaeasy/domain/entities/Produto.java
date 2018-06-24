package com.roberto.cotaeasy.domain.entities;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String nome;

    @NotNull
    @Value("${some.key:false}")
    private boolean cotado;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCotado() {
        return cotado;
    }

    public void setCotado(boolean cotado) {
        this.cotado = cotado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
