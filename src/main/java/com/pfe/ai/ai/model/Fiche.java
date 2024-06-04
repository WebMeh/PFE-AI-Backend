package com.pfe.ai.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fiches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fiche {
    @Id
    @GeneratedValue
    private Long id;
    private String filename;
    @Column(unique = true)
    private String path;
    @Column(nullable = false)
    private Long teacher_id;
    private String uploadedAt;

    // test git
    // another test
}
