package com.example.finalproject.controller;

import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.dto.EnrollmentResponse;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping("/students/{id}/courses/{courseId}")
    public ResponseEntity<EnrollmentResponse> enroll(@PathVariable Long id, @PathVariable Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enroll(id, courseId));
    }

    @DeleteMapping("/students/{id}/courses/{courseId}")
    public ResponseEntity<Void> unenroll(@PathVariable Long id, @PathVariable Long courseId) {
        enrollmentService.unenroll(id, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<CourseResponse>> getStudentCourses(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getStudentCourses(id));
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<StudentResponse>> getCourseStudents(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getCourseStudents(id));
    }
}


