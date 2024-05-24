package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.User;

import java.util.List;

public interface CourseService {
    Cour createCourse(Cour course, String username);
    List<Cour> getTeacherCourses(String username);
    void enrollStudent(Long studentId, Long courseId);
    List<User> getEnrolledStudents(Long teacherId);
}
