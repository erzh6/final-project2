package com.example.finalproject.controller;

import com.example.finalproject.dto.CourseRequest;
import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.model.Course;
import com.example.finalproject.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(courseService.getAll(page, size, sortBy));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }
    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return ResponseEntity.ok(courseService.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponse> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
