package com.roberto.avitec.service;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.models.LoginModel;
import com.roberto.avitec.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Objects;

@Service
@Transactional
public class UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public RetornoBaseModel logar(LoginModel login) throws Exception {
        Usuario user = loginToUsuario(login);
        Usuario userEntity = usuarioRepository.findByUsuarioAndSenha(user.getUsuario(), user.getSenha());
        if (Objects.nonNull(userEntity)) {
            return new RetornoBaseModel<Usuario>(true, "Sucesso", userEntity);
        } else {
            return new RetornoBaseModel<Usuario>(false, "Falha", userEntity);
        }
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario(long id) {
        usuarioRepository.delete(id);
    }

    public Usuario consultarUsuario(long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario loginToUsuario(LoginModel loginModel) throws Exception {
        if (Objects.isNull(loginModel) ||
            Objects.isNull(loginModel.getUsuario()) ||
            Objects.isNull(loginModel.getSenha())) {
            throw new Exception("Nem todas as informações de login foram informadas.");
        }
        Usuario usuario = new Usuario();
        usuario.setUsuario(loginModel.getUsuario());
        usuario.setSenha(loginModel.getSenha());
        return usuario;
    }
}
