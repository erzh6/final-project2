package com.example.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Integer credits;
    private Long teacherId;
    private String teacherName;
}
