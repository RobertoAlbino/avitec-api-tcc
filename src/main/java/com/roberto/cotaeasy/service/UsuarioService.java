package com.roberto.cotaeasy.service;

import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.domain.models.LoginModel;
import com.roberto.cotaeasy.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) throws Exception {
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario(long id) {
        usuarioRepository.delete(id);
    }

    public Usuario consultarUsuario(long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario findFirstByLogin(LoginModel loginModel) {
        return usuarioRepository.findFirstByLogin(loginModel.getLogin());
    }
}
