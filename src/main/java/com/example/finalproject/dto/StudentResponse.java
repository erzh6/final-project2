package com.example.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private LocalDate enrollmentDate;
}
