package com.example.finalproject.dto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {

    String firstName;
    String lastName;
    String email;
    long phone;
    int age;
    double gpa;
}
