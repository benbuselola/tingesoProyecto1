package com.example.backendBanco.services;

import com.example.backendBanco.entities.LoanTypeEntity;
import com.example.backendBanco.repositories.LoanTypeRepository;
import com.example.backendBanco.repositories.ClientRepository;
import com.example.backendBanco.entities.ClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Locale;
import java.util.Optional;

import java.util.List;

@Service
public class LoanTypeService {

        @Autowired
        private LoanTypeRepository loanTypeRepository;
        @Autowired
        private ClientRepository clientRepository;

        public List<LoanTypeEntity> getLoanTypes() {
                return loanTypeRepository.findAll();
        }

        public LoanTypeEntity getLoanTypeById(Long id) {
                Optional<LoanTypeEntity> loanType = loanTypeRepository.findById(id);
                return loanType.orElse(null);
        }

        public LoanTypeEntity saveLoanType(LoanTypeEntity loanType) {
                return loanTypeRepository.save(loanType);
        }

        public LoanTypeEntity updateLoanType(LoanTypeEntity loanType) {
                Optional<LoanTypeEntity> existingLoanType = loanTypeRepository.findById(loanType.getId());
                if (existingLoanType.isPresent()) {
                        return loanTypeRepository.save(loanType);
                } else {
                        throw new RuntimeException("El tipo de préstamo no existe");
                }
        }

        public boolean deleteLoanType(Long id) throws Exception {
                Optional<LoanTypeEntity> loanType = loanTypeRepository.findById(id);
                if (loanType.isPresent()) {
                        loanTypeRepository.deleteById(id);
                        return true;
                } else {
                        throw new Exception("El tipo de préstamo con ID " + id + " no se encuentra");
                }
        }

        @Transactional
        public void deleteLoanTypeByRut(String rut) throws Exception {
                List<LoanTypeEntity> loanType = loanTypeRepository.findByRutClient(rut);
                if (!loanType.isEmpty()) {
                        loanTypeRepository.deleteByRutClient(rut);
                } else {
                        throw new Exception("El cliente con RUT " + rut + " no tiene préstamos asociados");
                }
        }

        public List<LoanTypeEntity> getLoanByRut(String rut) {
                return loanTypeRepository.findByRutClient(rut);
        }

        public LoanTypeEntity requestLoan(String rutClient, String type, int years, double interestRate, double propertyValue) {
                ClientEntity client = clientRepository.findByRut(rutClient);
                if (client == null) {
                        throw new IllegalArgumentException("El RUT del cliente no está registrado en la base de datos");
                }

                LoanTypeEntity loanType = new LoanTypeEntity();
                loanType.setRutClient(rutClient);
                loanType.setType(type);

                double financeRate = 0;
                int maxYears = 0;
                double minInterestRate = 0;
                double maxInterestRate = 0;

                if (type.toLowerCase(Locale.ROOT).equals("primera vivienda")) {
                        maxYears = 30;
                        financeRate = 0.8;
                        minInterestRate = 3.5;
                        maxInterestRate = 5.0;
                } else if (type.toLowerCase(Locale.ROOT).equals("segunda vivienda")) {
                        maxYears = 20;
                        financeRate = 0.7;
                        minInterestRate = 4.0;
                        maxInterestRate = 6.0;
                } else if (type.toLowerCase(Locale.ROOT).equals("propiedad comercial")) {
                        maxYears = 25;
                        financeRate = 0.6;
                        minInterestRate = 5.0;
                        maxInterestRate = 7.0;
                } else if (type.toLowerCase(Locale.ROOT).equals("remodelacion")) {
                        maxYears = 15;
                        financeRate = 0.5;
                        minInterestRate = 4.5;
                        maxInterestRate = 6.0;
                } else {
                        throw new IllegalArgumentException("Tipo de financiamiento no válido");
                }

                if (years > maxYears) {
                        throw new IllegalArgumentException("El plazo supera el máximo permitido para el tipo de financiamiento " + type);
                }

                if (interestRate < minInterestRate || interestRate > maxInterestRate) {
                        throw new IllegalArgumentException("La tasa de interés no está dentro del rango permitido para el tipo de financiamiento " + type);
                }

                double financedAmount = propertyValue * financeRate;

                double interestMonthly = (interestRate / 100) / 12;
                int numberOfPayments = years * 12;
                double monthlyPayment = financedAmount *((interestMonthly * Math.pow(1 + interestMonthly, numberOfPayments)) / (Math.pow(1 + interestMonthly, numberOfPayments) - 1));

                double insurance= financedAmount * 0.0003;
                double fireInsurance = 20000;


                double totalMonthlyPayment = monthlyPayment + fireInsurance + insurance;

                double totalCost = (totalMonthlyPayment * numberOfPayments) + (financedAmount * 0.01);
                loanType.setYears(years);
                loanType.setInterestRate(interestRate);
                loanType.setFinanceRate(financeRate);
                loanType.setMonthlyPayment(Math.round(totalMonthlyPayment));
                loanType.setTotalCost(Math.round(totalCost));
                return loanTypeRepository.save(loanType);
        }

}
