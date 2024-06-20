package com.pfe.ai.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createdDate;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id") // Optional: Specify foreign key column name
    @JsonIgnore
    private Question question;
}
