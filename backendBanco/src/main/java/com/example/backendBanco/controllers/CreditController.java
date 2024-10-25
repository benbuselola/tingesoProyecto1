package com.example.backendBanco.controllers;

import com.example.backendBanco.entities.CreditEntity;
import com.example.backendBanco.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/credit")
@CrossOrigin("*")

public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping("/")
    public ResponseEntity<List<CreditEntity>> listCredits() {
        List<CreditEntity> credits = creditService.getCredits();
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditEntity> getCreditById(@PathVariable Long id) {
        CreditEntity credit = creditService.getCreditById(id);
        return ResponseEntity.ok(credit);
    }

    @PostMapping("/")
    public ResponseEntity<CreditEntity> saveCredit(@RequestBody CreditEntity credit) {
        CreditEntity creditNew = creditService.saveCredit(credit);
        return ResponseEntity.ok(creditNew);
    }

    @PutMapping("/")
    public ResponseEntity<CreditEntity> updateCredit(@RequestBody CreditEntity credit){
        CreditEntity creditUpdated = creditService.updateCredit(credit);
        return ResponseEntity.ok(creditUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCreditById(@PathVariable Long id) throws Exception {
        var isDeleted = creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/simulate")
    public ResponseEntity<Double> simulateCredit(@RequestBody CreditEntity creditRequest) {
        double monthlyPayment = creditService.calculateCredit(
                creditRequest.getAmount(),
                creditRequest.getYears(),
                creditRequest.getInterestRate()
        );

        return ResponseEntity.ok(monthlyPayment);
    }

}
