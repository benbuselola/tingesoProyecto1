package services;

import com.example.backendBanco.entities.CreditEntity;
import com.example.backendBanco.entities.CreditEvaluationEntity;
import com.example.backendBanco.repositories.CreditRepository;
import com.example.backendBanco.services.CreditService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
public class CreditServiceTest {
    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private CreditService creditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllCredits() {
        creditService.getCredits();
        verify(creditRepository).findAll();
    }
    @Test
    public void saveCredit(){
        CreditEntity credit = new CreditEntity(1L, 1000000, 10, 3.5,70000 , "primera vivienda");
        when(creditRepository.save(credit)).thenReturn(credit);
        CreditEntity saveCredit = creditService.saveCredit(credit);
        assertThat(saveCredit).isEqualTo(credit);
    }
    @Test
    public void getCreditById(){
        CreditEntity credit = new CreditEntity(1L, 1000000, 10, 3.5,70000 , "primera vivienda");
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        CreditEntity creditFound = creditService.getCreditById(1L);
        assertThat(creditFound).isEqualTo(credit);
    }
    @Test
    public void updateCredit(){
        CreditEntity credit = new CreditEntity(1L, 1000000, 10, 3.5,70000 , "primera vivienda");
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        when(creditRepository.save(credit)).thenReturn(credit);
        CreditEntity result = creditService.updateCredit(credit);
        assertThat(result).isEqualTo(credit);
    }
    @Test
    public void updateCreditNotExist() {
        CreditEntity credit = new CreditEntity(1L, 1000000, 10, 3.5, 70000, "primera vivienda");
        when(creditRepository.findById(1L)).thenReturn(Optional.empty());

        CreditEntity updatedCredit = creditService.updateCredit(credit);
        assertThat(updatedCredit).isNull();
    }
    @Test
    public void deleteCredit() throws Exception {
        CreditEntity credit = new CreditEntity(1L, 1000000, 10, 3.5,70000 , "primera vivienda");
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        boolean deleted = creditService.deleteCredit(1L);
        assertThat(deleted).isTrue();
    }

    @Test
    public void calculateCreditFirstHome() {
        double amount = 1000000;
        int years = 10;
        double interestRate = 3.5;
        String type = "primera vivienda";

        double result = creditService.calculateCredit(amount, years, interestRate, type);
        assertTrue(result > 0);

    }
    @Test
    public void calculateCreditSecondHome() {
        double amount = 1000000;
        int years = 10;
        double interestRate = 5.5;
        String type = "segunda vivienda";

        double result = creditService.calculateCredit(amount, years, interestRate, type);
        assertTrue(result > 0);

    }
    @Test
    public void calculateCreditComercialProperty() {
        double amount = 1000000;
        int years = 10;
        double interestRate = 5.5;
        String type = "propiedad comercial";

        double result = creditService.calculateCredit(amount, years, interestRate, type);
        assertTrue(result > 0);
    }
    @Test
    public void calculateCreditRemodeling () {
        double amount = 1000000;
        int years = 10;
        double interestRate = 5.5;
        String type = "remodelación";

        double result = creditService.calculateCredit(amount, years, interestRate, type);
        assertTrue(result > 0);
    }
    @Test
    public void calculateCreditExceedsMaxYears() {
        double amount = 1000000;
        int years = 35; // Excede el máximo para "primera vivienda"
        double interestRate = 4.5;
        String type = "primera vivienda";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditService.calculateCredit(amount, years, interestRate, type);
        });
        assertEquals("El plazo supera el máximo permitido para el tipo de financiamiento primera vivienda", exception.getMessage());
    }
    @Test
    public void calculateCreditInvalidType() {
        double amount = 1000000;
        int years = 10;
        double interestRate = 3.5;
        String type = "vivienda";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditService.calculateCredit(amount, years, interestRate, type);
        });
        assertEquals("Tipo de financiamiento no válido: vivienda", exception.getMessage());
    }
    @Test
    public void calculateCreditExceedsInterestRate() {
        double amount = 1000000;
        int years = 10;
        double interestRate = 10;
        String type = "primera vivienda";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditService.calculateCredit(amount, years, interestRate, type);
        });
        assertEquals("La tasa de interés no está dentro del rango permitido para el tipo de financiamiento primera vivienda", exception.getMessage());
    }
    @Test
    public void calculateCreditBelowInterestRate(){
        double amount = 1000000;
        int years = 10;
        double interestRate = 1;
        String type = "primera vivienda";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditService.calculateCredit(amount, years, interestRate, type);
        });
        assertEquals("La tasa de interés no está dentro del rango permitido para el tipo de financiamiento primera vivienda", exception.getMessage());
    }
}
