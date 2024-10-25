package com.example.backendBanco.controllers;

import com.example.backendBanco.entities.CreditEvaluationEntity;
import com.example.backendBanco.services.CreditEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-evaluations")
@CrossOrigin("*")

public class CreditEvaluationController {
    @Autowired
    CreditEvaluationService creditEvaluationService;

    @GetMapping("/")
    public ResponseEntity<List<CreditEvaluationEntity>> listCreditEvaluations() {
        List<CreditEvaluationEntity> creditEvaluations = creditEvaluationService.getCreditEvaluations();
        return ResponseEntity.ok(creditEvaluations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditEvaluationEntity> getCreditEvaluationById(@PathVariable Long id) {
        CreditEvaluationEntity creditEvaluation = creditEvaluationService.getCreditEvaluationById(id);
        return ResponseEntity.ok(creditEvaluation);
    }

    @GetMapping("/client/{rut}")
    public ResponseEntity<List<CreditEvaluationEntity>> listCreditEvaluationsByClient(@PathVariable("rut") String rut) {
        List<CreditEvaluationEntity> creditEvaluations = creditEvaluationService.getCreditEvaluationsByClient(rut);
        return ResponseEntity.ok(creditEvaluations);
    }

    @PostMapping("/")
    public ResponseEntity<CreditEvaluationEntity> saveCreditEvaluation(@RequestBody CreditEvaluationEntity creditEvaluation) {
        CreditEvaluationEntity creditEvaluationNew = creditEvaluationService.saveCreditEvaluation(creditEvaluation);
        return ResponseEntity.ok(creditEvaluationNew);
    }

    @PutMapping("/")
    public ResponseEntity<CreditEvaluationEntity> updateCreditEvaluation(@RequestBody CreditEvaluationEntity creditEvaluation){
        CreditEvaluationEntity creditEvaluationUpdated = creditEvaluationService.updateCreditEvaluation(creditEvaluation);
        return ResponseEntity.ok(creditEvaluationUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCreditEvaluationById(@PathVariable Long id) throws Exception {
        var isDeleted = creditEvaluationService.deleteCreditEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
