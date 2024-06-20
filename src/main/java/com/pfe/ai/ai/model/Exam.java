package com.pfe.ai.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne // ManyToOne with User (student)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne // ManyToOne with Course
    @JoinColumn(name = "course_id")
    private Cour course;

    // OneToMany relationship with Question
    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> questions;
}
