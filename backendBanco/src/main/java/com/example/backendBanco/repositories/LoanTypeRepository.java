
package com.example.backendBanco.repositories;

import com.example.backendBanco.entities.LoanTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanTypeEntity, Long> {
    List<LoanTypeEntity> findByRutClient(String rutClient);
    @Modifying
    @Query("DELETE FROM LoanTypeEntity l WHERE l.rutClient = :rutClient")
    void deleteByRutClient(@Param("rutClient") String rutClient);
}