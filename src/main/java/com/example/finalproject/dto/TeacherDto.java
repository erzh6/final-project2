package com.example.finalproject.dto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherDto {
    String firstName;
    String lastName;
    String email;
    int age;
    String specialization;
}
