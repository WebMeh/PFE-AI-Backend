package com.pfe.ai.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long rate;
    private String category;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses") // ManyToMany with User (students)
    private List<User> students;

    @ManyToOne // ManyToOne with User (teacher)
    @JoinColumn(name = "teacher_id")
    private User teacher;

}
