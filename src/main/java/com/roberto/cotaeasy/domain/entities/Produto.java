package com.roberto.cotaeasy.domain.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
