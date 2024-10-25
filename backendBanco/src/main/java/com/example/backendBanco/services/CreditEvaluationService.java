package com.example.backendBanco.services;

import com.example.backendBanco.entities.CreditEvaluationEntity;
import com.example.backendBanco.repositories.CreditEvaluationRepository;
import com.example.backendBanco.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@Service
public class CreditEvaluationService {
    @Autowired
    CreditEvaluationRepository creditEvaluationRepository;

    public List<CreditEvaluationEntity> getCreditEvaluations() {
        return creditEvaluationRepository.findAll();
    }

    public CreditEvaluationEntity getCreditEvaluationById(Long id) {
        Optional<CreditEvaluationEntity> creditEvaluation = creditEvaluationRepository.findById(id);
        return creditEvaluation.orElseThrow(() -> new RuntimeException("La evaluación de crédito no existe."));
    }

    public List<CreditEvaluationEntity> getCreditEvaluationsByClient(String rut) {
        return creditEvaluationRepository.findByRut(rut);
    }

    public CreditEvaluationEntity saveCreditEvaluation(CreditEvaluationEntity creditEvaluation) {
        return creditEvaluationRepository.save(creditEvaluation);
    }

    public CreditEvaluationEntity updateCreditEvaluation(CreditEvaluationEntity creditEvaluation) {
        Optional<CreditEvaluationEntity> existingEvaluation = creditEvaluationRepository.findById(creditEvaluation.getId());
        if (existingEvaluation.isPresent()) {
            return creditEvaluationRepository.save(creditEvaluation);
        } else {
            throw new RuntimeException("La evaluación de crédito no existe para ser actualizada.");
        }
    }

    public boolean deleteCreditEvaluation(Long id) throws Exception {
        Optional<CreditEvaluationEntity> creditEvaluation = creditEvaluationRepository.findById(id);
        if (creditEvaluation.isPresent()) {
            creditEvaluationRepository.deleteById(id);
            return true;
        } else {
            throw new Exception("La evaluación de crédito no se encontró para ser eliminada.");
        }
    }
}
