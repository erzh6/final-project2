package com.example.finalproject;

import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.mapper.Mapper;
import com.example.finalproject.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTest {

    @Test
    void StudentResponse() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Азамат");
        student.setLastName("Кубанычбеков");
        student.setEmail("azamat.k@example.com");

        StudentResponse response = Mapper.toStudentResponse(student);

        assertEquals(1L, response.getId());
        assertEquals("Азамат", response.getFirstName());
        assertEquals("azamat.k@example.com", response.getEmail());
    }
}