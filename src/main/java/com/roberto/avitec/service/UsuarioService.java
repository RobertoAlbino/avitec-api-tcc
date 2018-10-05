package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.models.LoginModel;
import com.roberto.avitec.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Service
@Transactional
public class UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void removerUsuario(long id) {
        usuarioRepository.delete(id);
    }

    public Usuario consultarUsuario(long id) {
        return usuarioRepository.findOne(id);
    }
}
