package com.roberto.controle.domain.entities;

import com.roberto.controle.utils.MD5Utils;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(length = 150, unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 10, max = 60)
    @Column(name = "senha", length = 60, nullable = false)
    private String senha;

    @Email
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        return this == o ? true : false;
    }

    @Override
    public String toString() {
        return "Login: " + login +
               "Senha: " + senha +
               "E-mail: " + email;
    }
}
