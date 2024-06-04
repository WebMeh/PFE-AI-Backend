package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FicheRepository extends JpaRepository<Fiche, Long> {
    //List<Fiche> findByTeacher_id(Long teacherId);
}
