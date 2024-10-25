package com.example.backendBanco.repositories;

import com.example.backendBanco.entities.CreditEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CreditEvaluationRepository extends JpaRepository<CreditEvaluationEntity, Long> {
    public List<CreditEvaluationEntity> findByRut(String rut);
    @Query(value = "SELECT * FROM credit_evaluation WHERE credit_evaluation.rut = :rut", nativeQuery = true)
    CreditEvaluationEntity findByRutNativeQuery(@Param("rut") String rut);

}
