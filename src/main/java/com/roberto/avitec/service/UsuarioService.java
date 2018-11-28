package com.roberto.avitec.service;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.models.LoginModel;
import com.roberto.avitec.domain.models.UsuarioModel;
import com.roberto.avitec.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void validate(Usuario usuario) throws Exception {
        if (Objects.isNull(usuario.getNome())) {
            throw new Exception("Nome não informado");
        }
        if (Objects.isNull(usuario.getUsuario())) {
            throw new Exception("Usuário não informado");
        }
        if (Objects.isNull(usuario.getSenha())) {
            throw new Exception("Senha não informada");
        }
        if (Objects.isNull(usuario.getAdmin())) {
            throw new Exception("Tipo do usuário não informado");
        }
    }

    private void validateLogin(LoginModel login) throws Exception {
        if (Objects.isNull(login)) {
            throw new Exception("Login não informado");
        }
        if (Objects.isNull(login.getUsuario())) {
            throw new Exception("Usuário não informado");
        }
        if (Objects.isNull(login.getSenha())) {
            throw new Exception("Senha não informada");
        }

    }

    private void isUsuarioDuplicado(Usuario usuario) throws Exception{
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            throw new Exception("Nome de usuário não disponível");
        }
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

    public Usuario criarUsuario(UsuarioModel model) throws Exception {
        Usuario usuario = modelToEntity(model);
        isUsuarioDuplicado(usuario);
        validate(usuario);
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario(long id) {
        usuarioRepository.delete(id);
    }

    public Usuario consultarUsuario(long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario loginToUsuario(LoginModel login) throws Exception {
        validateLogin(login);
        Usuario usuario = new Usuario();
        usuario.setUsuario(login.getUsuario());
        usuario.setSenha(login.getSenha());
        return usuario;
    }

    public Usuario modelToEntity(UsuarioModel model) throws  Exception{
        Usuario usuario = new Usuario();
        usuario.setId(0);
        usuario.setNome(model.getNome());
        usuario.setUsuario(model.getUsuario());
        usuario.setSenha(model.getSenha());
        usuario.setAdmin(model.getAdmin());
        return usuario;
    }
}
