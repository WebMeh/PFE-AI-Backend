package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
/* Pout React app
@CrossOrigin(origins = "http://localhost:5173")
*/
public class TeacherController {
    private final CourseService courseService;

    // Create new course by the teacher
    @PostMapping("/create-course")
    public ResponseEntity<?> createCours(@AuthenticationPrincipal UserDetails teacher, @RequestBody Cour cour){
        Cour createdCourse = courseService.createCourse(cour ,teacher.getUsername());
        if (createdCourse == null)
            return ResponseEntity.ok("Echec de créer le cours");
        return ResponseEntity.ok(createdCourse);
    }

    // Update course
    @PutMapping("update-course/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Cour updatedCourse){
        Cour cour = courseService.updateCourse(courseId, updatedCourse);
        if (cour == null) return ResponseEntity.ok("Course not found with id " + courseId);
        return ResponseEntity.ok(cour);
    }

    // Get all courses of a teacher
    @GetMapping("/courses/all")
    public ResponseEntity<?> getTeacherCourses(@AuthenticationPrincipal UserDetails teacher) {
        List<Cour> courses = courseService.getTeacherCourses(teacher.getUsername());
        return (courses != null && !courses.isEmpty()) ? ResponseEntity.ok(courses) : ResponseEntity.ok("Pas de cours pour vous !");
    }
}
