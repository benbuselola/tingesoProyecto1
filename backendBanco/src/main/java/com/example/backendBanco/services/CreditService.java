package com.example.backendBanco.services;



import com.example.backendBanco.entities.CreditEntity;
import com.example.backendBanco.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Locale;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;

    public List<CreditEntity> getCredits(){
        return creditRepository.findAll();
    }

    public CreditEntity saveCredit(CreditEntity credit){
        if (creditRepository.findById(credit.getId()).isPresent()) {
            return null;
        }
        return creditRepository.save(credit);
    }

    public CreditEntity getCreditById(Long creditId){
        return creditRepository.findById(creditId).get();
    }

    public CreditEntity updateCredit(CreditEntity credit) {
        if (creditRepository.findById(credit.getId()).isEmpty()) {
            return null;
        }
        return creditRepository.save(credit);
    }

    public boolean deleteCredit(Long id) throws Exception {
        try{
            creditRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    public double calculateCredit(double amount, int years, double interestRate, String type) {
        int maxYears = 0;
        double minInterestRate = 0;
        double maxInterestRate = 0;

        String normalizedType = type.trim().toLowerCase(Locale.ROOT);

        if (normalizedType.equals("primera vivienda")) {
            maxYears = 30;
            minInterestRate = 3.5;
            maxInterestRate = 5.0;
        } else if (normalizedType.equals("segunda vivienda")) {
            maxYears = 20;
            minInterestRate = 4.0;
            maxInterestRate = 6.0;
        } else if (normalizedType.equals("propiedad comercial")) {
            maxYears = 25;
            minInterestRate = 5.0;
            maxInterestRate = 7.0;
        } else if (normalizedType.equals("remodelación")) {
            maxYears = 15;
            minInterestRate = 4.5;
            maxInterestRate = 6.0;
        } else {
            throw new IllegalArgumentException("Tipo de financiamiento no válido: " + type);
        }

        if (years > maxYears) {
            throw new IllegalArgumentException("El plazo supera el máximo permitido para el tipo de financiamiento " + type);
        }

        if (interestRate < minInterestRate || interestRate > maxInterestRate) {
            throw new IllegalArgumentException("La tasa de interés no está dentro del rango permitido para el tipo de financiamiento " + type);
        }

        double interest_monthly = (interestRate / 100) / 12;
        int number_of_payments = years * 12;
        double monthlyPayment = amount * ((interest_monthly * Math.pow(1 + interest_monthly, number_of_payments)) / (Math.pow(1 + interest_monthly, number_of_payments) - 1));

        return Math.round(monthlyPayment);
    }

}
