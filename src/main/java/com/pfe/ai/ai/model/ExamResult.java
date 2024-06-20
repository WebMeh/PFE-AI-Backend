package com.pfe.ai.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class ExamResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double result;
    @ManyToOne
    private Exam exam;
    @ManyToOne
    private User student;

}
