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

import java.util.ArrayList;
import java.util.Date;
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
    public Cour createCourse(Cour course, Long teacherId) {
        User teacher = userRepository.findById(teacherId).orElse(null);
        if(teacher == null) return null;
        course.setTeacher(teacher); // Set the teacher of the course
        return courseRepository.save(course);
    }

    @Override
    public Cour updateCourse(Long courseId, Cour course) {
        // Check if course exists
        Cour existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) return null;

        // Update non-null fields (avoid overwriting unrelated changes)
        existingCourse.setTitle(course.getTitle() != null ? course.getTitle() : existingCourse.getTitle());
        existingCourse.setDescription(course.getDescription() != null ? course.getDescription() : existingCourse.getDescription());

        // Update other fields as needed (e.g., learning materials)

        return courseRepository.save(existingCourse); // Save the updated course
    }

    @Override
    public void deleteCourse(Long courseId) {
        // Check if course exists
        Cour course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }

        // Delete the course (cascade deletion might handle enrolled students)
        courseRepository.delete(course);
    }

    @Override
    public List<Cour> getTeacherCourses(Long teacherId) {
        User teacher = userRepository.findById(teacherId).orElse(null);
        if(teacher == null) return null;
        return courseRepository.findByTeacher(teacher);
    }

    @Override
    public List<Cour> getStudentCourses(Long studentId) {
        User student = userRepository.findById(studentId).orElse(null);
        if (student == null) return null;
        List<Enrollment> enrollments = enrollmentRepository.findAll(); // Get all enrollments
        List<Cour> studentCourses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(student.getId())) {
                studentCourses.add(enrollment.getCourse());
            }
        }
        return studentCourses;
    }

    @Override
    public Enrollment enrollStudent(Long userId, Long courseId) {
        // Get the course from the database
        Cour course = courseRepository.findById(courseId).orElse(null);
        // Get the student from the database
        User student = userRepository.findById(userId).orElse(null);
        if (course == null || student == null) return null;
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(new Date()); // Set enrollment date (optional)
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<User> getEnrolledStudents(Long teacherId) {
        return  null;
    }

    @Override
    public Cour getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Cour> getAllCourses() {
        return courseRepository.findAll();
    }
}
