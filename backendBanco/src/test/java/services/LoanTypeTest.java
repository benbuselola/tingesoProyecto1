package com.example.backendBanco.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backendBanco.entities.LoanTypeEntity;
import com.example.backendBanco.repositories.LoanTypeRepository;
import com.example.backendBanco.entities.ClientEntity;
import com.example.backendBanco.repositories.ClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class LoanTypeTest {

    @Mock
    private LoanTypeRepository loanTypeRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private LoanTypeService loanTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLoanTypes() {
        List<LoanTypeEntity> loanTypes = loanTypeService.getLoanTypes();
        verify(loanTypeRepository).findAll();
    }

    @Test
    public void getLoanTypeById() {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(loanType));
        LoanTypeEntity loanTypeFound = loanTypeService.getLoanTypeById(1L);
        assertThat(loanTypeFound).isEqualTo(loanType);
    }

    @Test
    public void saveLoanType() {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.save(loanType)).thenReturn(loanType);
        LoanTypeEntity savedLoanType = loanTypeService.saveLoanType(loanType);
        assertThat(savedLoanType).isEqualTo(loanType);
    }

    @Test
    public void updateLoanType() {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(loanType));
        when(loanTypeRepository.save(loanType)).thenReturn(loanType);
        LoanTypeEntity result = loanTypeService.updateLoanType(loanType);
        assertThat(result).isEqualTo(loanType);
    }

    @Test
    public void deleteLoanType() throws Exception {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(loanType));
        loanTypeService.deleteLoanType(1L);
        verify(loanTypeRepository).deleteById(1L);
    }

    @Test
    public void deleteLoanTypeNotExist() {
        when(loanTypeRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(Exception.class, () -> loanTypeService.deleteLoanType(1L));
    }

    @Test
    public void deleteLoanByRut() throws Exception {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.findByRutClient("12345678-9")).thenReturn(java.util.List.of(loanType));
        loanTypeService.deleteLoanTypeByRut("12345678-9");
        verify(loanTypeRepository).deleteByRutClient("12345678-9");
    }

    @Test
    public void getLoanByRut() {
        LoanTypeEntity loanType = new LoanTypeEntity(1L, "primera vivienda", 10, 3.5, 80, "12345678-9", 1000000, 10000000);
        when(loanTypeRepository.findByRutClient("12345678-9")).thenReturn(java.util.List.of(loanType));
        List<LoanTypeEntity> loanTypeFound = loanTypeService.getLoanByRut("12345678-9");
        assertThat(loanTypeFound).contains(loanType);
    }

    @Test
    public void requestLoanFirstHome() {
        String rutClient = "12345678-9";
        String type = "primera vivienda";
        int years = 10;
        double interestRate = 3.5;
        double propertyValue = 1000000;
        double financeRate = 70;

        ClientEntity client = new ClientEntity(1L, rutClient, "Juan", "Perez", 30, "juan@example.com", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(rutClient)).thenReturn(client);

        LoanTypeEntity loanType = new LoanTypeEntity(1L, type, years, interestRate, financeRate, rutClient, propertyValue, propertyValue * financeRate / 100);
        when(loanTypeRepository.save(any(LoanTypeEntity.class))).thenReturn(loanType);

        LoanTypeEntity result = loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue, financeRate);

        assertNotNull(result);
        assertTrue(result.getMonthlyPayment() > 0);
    }
    @Test
    public void requestLoanSecondHome() {
        String rutClient = "12345678-9";
        String type = "segunda vivienda";
        int years = 10;
        double interestRate = 5;
        double propertyValue = 1000000;
        double financeRate = 60;

        ClientEntity client = new ClientEntity(1L, rutClient, "Juan", "Perez", 30, "juan@example.com", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(rutClient)).thenReturn(client);

        LoanTypeEntity loanType = new LoanTypeEntity(1L, type, years, interestRate, financeRate, rutClient, propertyValue, propertyValue * financeRate / 100);
        when(loanTypeRepository.save(any(LoanTypeEntity.class))).thenReturn(loanType);

        LoanTypeEntity result = loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue, financeRate);

        assertNotNull(result);
        assertTrue(result.getMonthlyPayment() > 0);
    }
    @Test
    public void requestLoanComecialProperty() {
        String rutClient = "12345678-9";
        String type = "propiedad comercial";
        int years = 10;
        double interestRate = 5;
        double propertyValue = 1000000;
        double financeRate = 50;

        ClientEntity client = new ClientEntity(1L, rutClient, "Juan", "Perez", 30, "juan@example.com", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(rutClient)).thenReturn(client);

        LoanTypeEntity loanType = new LoanTypeEntity(1L, type, years, interestRate, financeRate, rutClient, propertyValue, propertyValue * financeRate / 100);
        when(loanTypeRepository.save(any(LoanTypeEntity.class))).thenReturn(loanType);

        LoanTypeEntity result = loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue, financeRate);

        assertNotNull(result);
        assertTrue(result.getMonthlyPayment() > 0);
    }
    @Test
    public void requestLoanRemodeling() {
        String rutClient = "12345678-9";
        String type = "remodelacion";
        int years = 10;
        double interestRate = 5;
        double propertyValue = 1000000;
        double financeRate = 40;

        ClientEntity client = new ClientEntity(1L, rutClient, "Juan", "Perez", 30, "juan@example.com", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(rutClient)).thenReturn(client);

        LoanTypeEntity loanType = new LoanTypeEntity(1L, type, years, interestRate, financeRate, rutClient, propertyValue, propertyValue * financeRate / 100);
        when(loanTypeRepository.save(any(LoanTypeEntity.class))).thenReturn(loanType);

        LoanTypeEntity result = loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue, financeRate);

        assertNotNull(result);
        assertTrue(result.getMonthlyPayment() > 0);
    }
    @Test
    public void requestLoanExceedsMaxYears() {
        String rutClient = "12345678-9";
        String type = "segunda vivienda";
        int years = 25;
        double interestRate = 5.5;
        double propertyValue = 1000000;
        double financeRate = 70;
        ClientEntity client = new ClientEntity(1L, rutClient, "Juan", "Perez", 30, "juan@example.com", "12345678", "Calle Falsa 123", "1234");
        when(clientRepository.findByRut(rutClient)).thenReturn(client);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loanTypeService.requestLoan(rutClient, type, years, interestRate, propertyValue, financeRate);
        });
        assertEquals("El plazo supera el máximo permitido para el tipo de financiamiento segunda vivienda", exception.getMessage());
    }
}
