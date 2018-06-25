package com.roberto.cotaeasy.repository;

import com.roberto.cotaeasy.domain.entities.CotacaoLance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoLanceRepository extends JpaRepository<CotacaoLance, Long> {
    void deleteByCotacaoProdutoId(long produtoId);
    boolean existsByFornecedorEmailAndCotacaoProdutoId(String emailFornecedor, long idProduto);
}
