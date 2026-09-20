package com.example.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class EnrollmentRequest {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private LocalDate enrollmentDate;
    private Integer grade;
}
