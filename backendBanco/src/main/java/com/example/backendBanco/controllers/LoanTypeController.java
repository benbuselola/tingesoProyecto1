package com.example.backendBanco.controllers;
import com.example.backendBanco.entities.LoanTypeEntity;
import com.example.backendBanco.services.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loanType")
@CrossOrigin("*")

public class LoanTypeController {
    @Autowired
    LoanTypeService loanTypeService;

    @GetMapping("/")
    public ResponseEntity<List<LoanTypeEntity>> getAll() {
        List<LoanTypeEntity> loanTypes = loanTypeService.getLoanTypes();
        return ResponseEntity.ok(loanTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanTypeEntity> getLoanTypeById(@PathVariable Long id) {
        LoanTypeEntity loanType = loanTypeService.getLoanTypeById(id);
        return ResponseEntity.ok(loanType);
    }
    @PostMapping("/")
    public ResponseEntity<LoanTypeEntity> saveLoanType(@RequestBody LoanTypeEntity type) {
        LoanTypeEntity loanTypeNew = loanTypeService.saveLoanType(type);
        return ResponseEntity.ok(loanTypeNew);
    }
    @PutMapping("/")
    public ResponseEntity<LoanTypeEntity> updateLoanType(@RequestBody LoanTypeEntity type){
        LoanTypeEntity loanTypeUpdated = loanTypeService.updateLoanType(type);
        return ResponseEntity.ok(loanTypeUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLoanTypeById(@PathVariable Long id) throws Exception {
        var isDeleted = loanTypeService.deleteLoanType(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/client/{rut}")
    public ResponseEntity<Void> deleteLoanTypeByRut(@PathVariable String rut) {
        try {
            loanTypeService.deleteLoanTypeByRut(rut);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/client")
    public ResponseEntity<List<LoanTypeEntity>> getLoanByRut(@RequestParam String rut) {
        List<LoanTypeEntity> loan = loanTypeService.getLoanByRut(rut);
        if (loan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(loan);
    }
    @PostMapping("/request")
    public ResponseEntity<LoanTypeEntity> requestLoan(
            @RequestParam String rutClient,
            @RequestParam String type,
            @RequestParam int years,
            @RequestParam double interestRate,
            @RequestParam double propertyValue
    ) {
        LoanTypeEntity loanRequest = loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanRequest);
    }

}
