package com.example.finalproject.service;

import com.example.finalproject.dto.StudentRequest;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Page<StudentResponse> getAll(int page, int size, String sortBy);

    StudentResponse getById(Long id);

    List<StudentResponse> search(String name);

    StudentResponse create(StudentRequest request);

    StudentResponse update(Long id, StudentRequest request);

    void delete(Long id);
}
