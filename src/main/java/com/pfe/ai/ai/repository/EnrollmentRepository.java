package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Cour> findByStudent(User student);

}
