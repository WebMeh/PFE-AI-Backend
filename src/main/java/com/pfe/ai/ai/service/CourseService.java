package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    Cour createCourse(Cour course, Long teacherId);
    Cour updateCourse(Long courseId ,Cour course);
    void deleteCourse(Long courseId);
    List<Cour> getTeacherCourses(Long teacherId);
    List<Cour> getStudentCourses(Long studentId);
    Enrollment enrollStudent(Long userId, Long courseId);
    List<User> getEnrolledStudents(Long teacherId);

    List<Cour> getAllCourses();

    Cour getCourseById(Long courseId);
}
