package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Fiche;
import com.pfe.ai.ai.service.CourseService;
import com.pfe.ai.ai.system.Result;
import com.pfe.ai.ai.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/teacher")

public class TeacherController {
    private final CourseService courseService;

    // Create new course by the teacher
    @PostMapping("/create-course/{teacherId}")
    public ResponseEntity<?> createCourse(@PathVariable Long teacherId, @RequestBody Cour cour){
        Cour createdCourse = courseService.createCourse(cour ,teacherId);
        if (createdCourse == null)
            return ResponseEntity.ok("Echec de créer le cours");
        return ResponseEntity.ok(createdCourse);
    }

    // Get course by id
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseById(  @PathVariable Long courseId) {
        Cour course = courseService.getCourseById(courseId);
        if(course != null)
            return ResponseEntity.ok(course);
        return ResponseEntity.ok("Course Not Found with Id : "+ courseId);
    }

    // Update course
    @PutMapping("update-course/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Cour updatedCourse){
        Cour cour = courseService.updateCourse(courseId, updatedCourse);
        if (cour == null) return ResponseEntity.ok("Course not found with id " + courseId);
        return ResponseEntity.ok(cour);
    }

    // Get all courses of a teacher
    @GetMapping("/{teacherId}/courses/all")
    public ResponseEntity<?> getTeacherCourses(@PathVariable Long teacherId) {
        List<Cour> courses = courseService.getTeacherCourses(teacherId);
        if (courses != null){
            return ResponseEntity.ok(
                    new Result(
                            true, StatusCode.SUCCESS, "List of all courses of the teacher n°" + teacherId,
                            courses
                    )
            );
        } else {
            return ResponseEntity.ok(
                    new Result(true, StatusCode.SUCCESS, "Empty list ! No courses for you !" )
            );
        }
    }

    // Delete a course by id
    @DeleteMapping("/delete-course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        try {
            courseService.deleteCourse(courseId);
        }catch (RuntimeException e){
            System.out.println("course not found exception");
        }

        return ResponseEntity.ok("Course N°: "+courseId+" deleted successfully! ");
    }

    // Upload fiche
}
