
package com.example.backendBanco.repositories;

import com.example.backendBanco.entities.LoanTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanTypeEntity, Long> {


}