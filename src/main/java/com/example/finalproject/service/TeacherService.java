package com.example.finalproject.service;

import com.example.finalproject.dto.TeacherRequest;
import com.example.finalproject.dto.TeacherResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAll();

    TeacherResponse getById(Long id);

    void delete(Long id);

    TeacherResponse create(TeacherRequest request);
}
