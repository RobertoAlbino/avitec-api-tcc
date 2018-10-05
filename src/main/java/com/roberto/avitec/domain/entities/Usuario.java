package com.roberto.avitec.domain.entities;

import com.roberto.avitec.utils.MD5Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String nome;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String usuario;

    @NotNull
    @Size(min = 10, max = 60)
    @Column(name = "senha", length = 60)
    private String senha;

    @NotNull
    @Column(name = "admin")
    private Boolean admin;

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


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        this.senha = MD5Utils.encriptarSenha(senha);
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
