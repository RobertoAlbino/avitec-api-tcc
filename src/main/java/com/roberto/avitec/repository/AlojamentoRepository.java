package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Alojamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlojamentoRepository extends JpaRepository<Alojamento, Long> {

    List<Alojamento> findAll();
}
