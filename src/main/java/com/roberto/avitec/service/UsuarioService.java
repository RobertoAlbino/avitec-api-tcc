package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.enums.EPerfil;
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

    public Usuario criarUsuario(Usuario usuario) throws Exception {
        if (usuarioExistente(usuario.getEmail()))
            throw new Exception("E-mail já está em uso.");

        return usuarioRepository.save(usuario);
    }

    public boolean usuarioExistente(String email) {
        return usuarioRepository.findFirstByEmail(email) != null ? true : false;
    }

    public void removerUsuario(long id) {
        usuarioRepository.delete(id);
    }

    public Usuario consultarUsuario(long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario findFirstByLogin(LoginModel loginModel) {
        return usuarioRepository.findFirstByEmail(loginModel.getEmail());
    }

    public LinkedList<Usuario> getAllFornecedores() {
        return usuarioRepository.findAllByPerfil(EPerfil.FORNECEDOR);
    }
}
