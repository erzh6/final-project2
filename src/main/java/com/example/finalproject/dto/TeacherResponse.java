package com.example.finalproject.dto;

import com.example.finalproject.model.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TeacherResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String specialization;
}

