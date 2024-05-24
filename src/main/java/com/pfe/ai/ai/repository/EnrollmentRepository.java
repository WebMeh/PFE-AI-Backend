package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
