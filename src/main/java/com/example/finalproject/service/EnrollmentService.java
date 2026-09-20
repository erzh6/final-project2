package com.example.finalproject.service;

import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.dto.EnrollmentResponse;
import com.example.finalproject.dto.StudentResponse;

import java.util.List;

public interface EnrollmentService {
    void unenroll(Long id, Long courseId);

    List<CourseResponse> getStudentCourses(Long id);

    List<StudentResponse> getCourseStudents(Long id);

    EnrollmentResponse enroll(Long id, Long courseId);
}
