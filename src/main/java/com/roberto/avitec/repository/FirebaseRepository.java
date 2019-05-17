package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.Firebase;
import com.roberto.avitec.domain.enums.TipoEnvioPush;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseRepository extends JpaRepository<Firebase, Long> {

    Firebase findByTipoEnvio(TipoEnvioPush tipoEnvio);
}
