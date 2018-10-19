package com.roberto.avitec.controller;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Usuario;
import com.roberto.avitec.domain.models.LoginModel;
import com.roberto.avitec.domain.models.UsuarioModel;
import com.roberto.avitec.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/consultar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel consultarUsuario(@RequestBody Usuario usuario) throws Exception {
        if (usuario.getId() == 0)
            throw new Exception("Não é possível consultar sem um id.");

        Usuario usuarioEntidade = usuarioService.consultarUsuario(usuario.getId());
        return new RetornoBaseModel<Usuario>(true, "Usuário encontrado.", usuarioEntidade);
    }

    @PostMapping(value = "/logar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel logar(@RequestBody LoginModel login) throws Exception {
        return usuarioService.logar(login);
    }

    @PostMapping(value = "/criar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel criarUsuario(@RequestBody UsuarioModel model) throws Exception {
        try {
            Usuario novoUsuario = usuarioService.criarUsuario(model);
            return new RetornoBaseModel<Usuario>(true, "Usuário cadastrado com sucesso.", novoUsuario);
        } catch (Exception ex) {
            return new RetornoBaseModel<Usuario>(false, ex.getMessage() , null);
        }
    }

    @DeleteMapping(value = "/remover", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel removerUsuario(@RequestBody Usuario usuario) throws Exception {
        if (usuario.getId() == 0)
            throw new Exception("Não é possível excluir uma entidade sem id.");

        usuarioService.removerUsuario(usuario.getId());
        return new RetornoBaseModel(true, "Usuário removido com sucesso.", null);
    }
}
