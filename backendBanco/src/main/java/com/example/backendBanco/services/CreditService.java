package com.example.backendBanco.services;



import com.example.backendBanco.entities.CreditEntity;
import com.example.backendBanco.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
        public double calculateCredit(double amount, int years, double interestRate){
        double interest_monthly = (interestRate /100) / 12;
        int number_of_payments = years * 12;
        double monthlyPayment = amount *((interest_monthly * Math.pow(1 + interest_monthly, number_of_payments)) / (Math.pow(1 + interest_monthly, number_of_payments) - 1));
        return Math.round(monthlyPayment);
    }
}
