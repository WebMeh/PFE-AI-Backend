package com.pfe.ai.ai.service.impl;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.repository.CourseRepository;
import com.pfe.ai.ai.repository.EnrollmentRepository;
import com.pfe.ai.ai.repository.UserRepository;
import com.pfe.ai.ai.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Cour createCourse(Cour course, String username) {
        User teacher = userRepository.findByUsername(username).orElse(null);
        if(teacher == null) return null;
        course.setTeacher(teacher); // Set the teacher of the course
        return courseRepository.save(course);
    }

    @Override
    public List<Cour> getTeacherCourses(String username) {
        User teacher = userRepository.findByUsername(username).orElse(null);
        if(teacher == null) return null;
        return courseRepository.findByTeacher(teacher);
    }

    @Override
    public void enrollStudent(Long studentId, Long courseId) {

    }

    @Override
    public List<User> getEnrolledStudents(Long teacherId) {
        return  null;
    }
        /*
        User teacher = userRepository.findById(teacherId).orElseThrow();
        return teacher.getCourses().stream()
                .flatMap(course -> course.getStudents().stream())
                .distinct() // Remove duplicates if using Enrollment entity
                .collect(Collectors.toList());
    }

         */
}
