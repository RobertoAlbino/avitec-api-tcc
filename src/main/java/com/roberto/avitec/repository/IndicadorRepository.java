package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {

    @Query(value = "SELECT * FROM INDICADOR WHERE DATA IN (SELECT MAX(DATA) " +
                   "FROM INDICADOR GROUP BY ZONA ORDER BY DATA ASC) " +
                   "GROUP BY ZONA ORDER BY DATA ASC", nativeQuery = true)
    List<Indicador> getUltimosIndicadoresPorZona();
    List<Indicador> findByZona(Integer zona);
}
