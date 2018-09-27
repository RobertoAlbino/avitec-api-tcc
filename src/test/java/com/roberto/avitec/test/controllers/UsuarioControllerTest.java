package com.roberto.avitec.test.controllers;

import com.roberto.avitec.controllers.UsuarioController;
import com.roberto.avitec.domain.entities.Usuario;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {
    @Mock
    private UsuarioController usuarioController;

    private Usuario usuario;

    @Before
    public void inicializar() {
        usuario = mock(Usuario.class);
    }

    @Ignore
    public void testarSeAtualizaoDeUsuarioBloqueiaUsuarioSemIdInformado() throws Exception {
        usuarioController.atualizarUsuario(this.usuario);
    }
}
