package com.roberto.cotaeasy.repository;

import com.roberto.cotaeasy.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    LinkedList<Produto> getAllByUsuarioId(long idUsuario);
}
