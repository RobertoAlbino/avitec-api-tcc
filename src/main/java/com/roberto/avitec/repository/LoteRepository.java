package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
    Lote existsByAtivoTrue();
}

