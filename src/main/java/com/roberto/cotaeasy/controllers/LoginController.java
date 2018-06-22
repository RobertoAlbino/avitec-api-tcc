package com.roberto.cotaeasy.controllers;

import com.roberto.cotaeasy.domain.base.RetornoBaseModel;
import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.domain.models.LoginModel;
import com.roberto.cotaeasy.service.UsuarioService;
import com.roberto.cotaeasy.utils.MD5Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(LoginController.class);
    private RetornoBaseModel objetoRetorno;
    private UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/logar", consumes = "application/json",  produces="application/json")
    public RetornoBaseModel logar(@RequestBody LoginModel loginModel) throws Exception {
        Usuario usuarioEntidade = usuarioService.findFirstByLogin(loginModel);
        if (usuarioEntidade != null && usuarioEntidade.getSenha().equals(MD5Utils.encriptarSenha(loginModel.getSenha()))) {
            return new RetornoBaseModel<Usuario>(true, "Usuário válido.", usuarioEntidade);
        } else {
            return new RetornoBaseModel<Usuario>(false, "Usuário não cadastrado.", usuarioEntidade);
        }
    }
}
