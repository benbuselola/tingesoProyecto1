package com.example.backendBanco.services;

import com.example.backendBanco.entities.LoanTypeEntity;
import com.example.backendBanco.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import java.util.List;

@Service
public class LoanTypeService {

        @Autowired
        private LoanTypeRepository loanTypeRepository;


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


}
