package com.roberto.cotaeasy.repository;

import com.roberto.cotaeasy.domain.entities.Usuario;
import com.roberto.cotaeasy.domain.enums.EPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findFirstByEmail(String email);
    LinkedList<Usuario> findAllByPerfil(EPerfil perfil);
}
