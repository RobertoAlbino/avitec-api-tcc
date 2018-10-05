package com.roberto.avitec.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String descricao;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_alojamento")
    private Alojamento alojamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Alojamento getAlojamento() {
        return alojamento;
    }

    public void setAlojamento(Alojamento alojamento) {
        this.alojamento = alojamento;
    }
}
