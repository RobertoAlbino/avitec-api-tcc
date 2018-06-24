package com.roberto.cotaeasy.domain.entities;

import com.roberto.cotaeasy.domain.enums.EPerfil;
import com.roberto.cotaeasy.utils.MD5Utils;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 150)
    @Column(length = 150)
    private String nome;

    @NotNull
    @Size(min = 10, max = 60)
    @Column(name = "senha", length = 60)
    private String senha;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Size(max = 15)
    private String telefone;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EPerfil perfil;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        this.senha = MD5Utils.encriptarSenha(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(EPerfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
               "Senha: " + senha +
               "E-mail: " + email;
    }
}
