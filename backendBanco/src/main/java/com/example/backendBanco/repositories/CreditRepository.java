package com.example.backendBanco.repositories;

import com.example.backendBanco.entities.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {
    Optional<CreditEntity> findById(Long id);
}
