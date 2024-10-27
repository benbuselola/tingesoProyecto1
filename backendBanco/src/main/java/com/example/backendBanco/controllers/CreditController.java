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
    @GetMapping("/simulate")
    public ResponseEntity<?> simulateCredit(@RequestParam double amount,
                                            @RequestParam int years,
                                            @RequestParam double interestRate,
                                            @RequestParam String type) {
        try {
            double monthlyPayment = creditService.calculateCredit(amount, years, interestRate, type);
            return ResponseEntity.ok(monthlyPayment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }


}
