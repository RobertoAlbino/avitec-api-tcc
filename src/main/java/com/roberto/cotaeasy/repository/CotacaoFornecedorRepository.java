package com.roberto.cotaeasy.repository;

import com.roberto.cotaeasy.domain.entities.CotacaoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface CotacaoFornecedorRepository extends JpaRepository<CotacaoFornecedor, Long> {
    void deleteByCotacaoProdutoId(long produtoId);
    LinkedList<CotacaoFornecedor> getAllByFornecedorId(long idFornecedor);
}
