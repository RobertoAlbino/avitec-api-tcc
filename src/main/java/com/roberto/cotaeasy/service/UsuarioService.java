package com.roberto.controle.service;

import com.roberto.controle.domain.entities.Usuario;
import com.roberto.controle.domain.models.LoginModel;
import com.roberto.controle.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

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
