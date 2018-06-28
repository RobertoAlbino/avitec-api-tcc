package com.roberto.cotaeasy.repository;

import com.roberto.cotaeasy.domain.entities.LogEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEmailRepository extends JpaRepository<LogEmail, Long> {}
