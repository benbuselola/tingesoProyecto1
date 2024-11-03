package services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backendBanco.entities.CreditEvaluationEntity;
import com.example.backendBanco.repositories.CreditEvaluationRepository;
import com.example.backendBanco.services.CreditEvaluationService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
public class CreditEvaluationTest {

    @Mock
    private CreditEvaluationRepository creditEvaluationRepository;

    @InjectMocks
    private CreditEvaluationService creditEvaluationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //Ver este metodo

    @Test
    public void getCreditEvaluations() {
        creditEvaluationService.getCreditEvaluations();
        verify(creditEvaluationRepository).findAll();
    }
    @Test
    public void getCreditEvaluationById() {
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.findById(1L)).thenReturn(java.util.Optional.of(creditEvaluation));
        CreditEvaluationEntity creditEvaluationFound = creditEvaluationService.getCreditEvaluationById(1L);
        assertThat(creditEvaluationFound).isEqualTo(creditEvaluation);
    }

    @Test
    public void getCreditEvaluationByRut(){
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.findByRut("12345678-9")).thenReturn(java.util.List.of(creditEvaluation));
        List<CreditEvaluationEntity> creditEvaluationFound = creditEvaluationService.getCreditEvaluationsByClient("12345678-9");
        assertThat(creditEvaluationFound).contains(creditEvaluation);
    }

    @Test
    public void saveCreditEvaluation(){
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.save(creditEvaluation)).thenReturn(creditEvaluation);
        CreditEvaluationEntity saveCreditEvaluation = creditEvaluationService.saveCreditEvaluation(creditEvaluation);
        assertThat(saveCreditEvaluation).isEqualTo(creditEvaluation);
    }
    @Test
    public void updateCreditEvaluation(){
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.findById(1L)).thenReturn(java.util.Optional.of(creditEvaluation));
        when(creditEvaluationRepository.save(creditEvaluation)).thenReturn(creditEvaluation);
        CreditEvaluationEntity result = creditEvaluationService.updateCreditEvaluation(creditEvaluation);
        assertThat(result).isEqualTo(creditEvaluation);
    }
    //VER
    @Test
    public void updateCreditEvaluationNotExist() {
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class,
                () -> creditEvaluationService.updateCreditEvaluation(creditEvaluation));
        assertThat(exception.getMessage()).isEqualTo("La evaluación de crédito no existe para ser actualizada.");
    }
    @Test
    public void deleteCreditEvaluation() throws Exception {
        CreditEvaluationEntity creditEvaluation = new CreditEvaluationEntity(1L, 1000000, 10, 3.5, "", "primera vivienda");
        when(creditEvaluationRepository.findById(1L)).thenReturn(java.util.Optional.of(creditEvaluation));
        boolean deleted = creditEvaluationService.deleteCreditEvaluation(1L);
        assertThat(deleted).isTrue();
    }
    @Test
    public void deleteCreditEvaluationNotExist() {
        when(creditEvaluationRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> creditEvaluationService.deleteCreditEvaluation(1L));
        assertThat(exception.getMessage()).isEqualTo("La evaluación de crédito no se encontró para ser eliminada.");
    }
}
