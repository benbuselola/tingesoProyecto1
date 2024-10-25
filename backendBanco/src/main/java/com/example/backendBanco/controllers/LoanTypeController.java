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
    public ResponseEntity<List<LoanTypeEntity>> listLoanTypes() {
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

}
