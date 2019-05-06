package com.roberto.avitec.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "firebase")
public class Firebase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
