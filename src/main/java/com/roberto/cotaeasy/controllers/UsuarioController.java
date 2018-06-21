package com.roberto.controle.controllers;

import com.roberto.controle.domain.base.RetornoBaseModel;
import com.roberto.controle.domain.entities.Usuario;
import com.roberto.controle.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;
    private RetornoBaseModel objetoRetorno;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/criar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel criarUsuario(@RequestBody Usuario usuario) throws Exception {
        Usuario usuarioEntidade = usuarioService.criarUsuario(usuario);
        return new RetornoBaseModel<Usuario>(true, "Usuário criado com sucesso.", usuarioEntidade);
    }

    @PutMapping(value = "/atualizar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel atualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        if (usuario.getId() == 0)
            throw new Exception("Não é possível atualizar uma entidade sem id.");

        Usuario usuarioEntidade = usuarioService.criarUsuario(usuario);
        return new RetornoBaseModel<Usuario>(true, "Usuário atualizado com sucesso.", usuarioEntidade);
    }

    @DeleteMapping(value = "/remover", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel removerUsuario(@RequestBody Usuario usuario) throws Exception {
        if (usuario.getId() == 0)
            throw new Exception("Não é possível excluir uma entidade sem id.");

        usuarioService.removerUsuario(usuario.getId());
        return new RetornoBaseModel<Object>(true, "Usuário removido com sucesso.", new Object());
    }

    @GetMapping(value = "/consultar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel consultarUsuario(@RequestBody Usuario usuario) throws Exception {
        if (usuario.getId() == 0)
            throw new Exception("Não é possível consultar sem um id.");

        Usuario usuarioEntidade = usuarioService.consultarUsuario(usuario.getId());
        return new RetornoBaseModel<Usuario>(true, "Usuário criado com sucesso.", usuarioEntidade);
    }
}
