package com.example.finalproject.dto;

import com.example.finalproject.model.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

    private String title;
    private String description;
    private Integer credits;
    private Teacher teacher;
}
