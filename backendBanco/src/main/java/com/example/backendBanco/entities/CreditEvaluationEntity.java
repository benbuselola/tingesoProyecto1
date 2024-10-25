package com.example.backendBanco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Table(name = "credit_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreditEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private double amount;
    private int years;
    private double interestRate;
    private String status;
    private String rut;
}
