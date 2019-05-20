package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.models.ExtremosIndicadoresPorDiaModel;
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
    @Query(value = "SELECT  DATA as data, MAX(temperatura) as temperaturaMaxima, MAX(umidade) as umidadeMaxima"  +
                   ",MIN(temperatura) as temperaturaMinima, MIN(umidade) as umidadeMinima" +
                   " FROM INDICADOR WHERE ID_LOTE = ?1 GROUP BY DAY(DATA)", nativeQuery = true)
    Object[] extremosIndicadoresPorDiaLote(Long lote);
    List<Indicador> findByZona(Integer zona);
}
