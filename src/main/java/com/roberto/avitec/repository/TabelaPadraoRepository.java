package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.TabelaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaPadraoRepository extends JpaRepository<TabelaPadrao, Long> {}

