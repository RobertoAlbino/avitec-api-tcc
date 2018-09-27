package com.roberto.avitec.repository;

import com.roberto.avitec.domain.entities.LogEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEmailRepository extends JpaRepository<LogEmail, Long> {}
