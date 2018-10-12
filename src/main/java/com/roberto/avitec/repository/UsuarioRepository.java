package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsuarioAndSenha(String usuario, String senha);
}
