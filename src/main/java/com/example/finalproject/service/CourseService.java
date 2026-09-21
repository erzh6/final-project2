package com.example.finalproject.service;

import com.example.finalproject.dto.CourseRequest;
import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CourseService {
    Page<CourseResponse> getAll(int page, int size, String sortBy);

    CourseResponse getById(Long id);

    CourseResponse update(Long id, CourseRequest request);

    void delete(Long id);

    CourseResponse create(CourseRequest request);

    Course findCourseById(Long id);
}
