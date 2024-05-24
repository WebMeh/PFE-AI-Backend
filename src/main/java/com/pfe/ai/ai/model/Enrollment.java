package com.pfe.ai.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "enrollment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // ManyToOne with Course
    @JoinColumn(name = "course_id")
    private Cour course;

    @ManyToOne // ManyToOne with User (student)
    @JoinColumn(name = "student_id")
    private User student;

    private Date enrollmentDate; // Optional field for enrollment date
}
