package com.roberto.controle.repository;

import com.roberto.controle.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findFirstByLogin(String login);
}
