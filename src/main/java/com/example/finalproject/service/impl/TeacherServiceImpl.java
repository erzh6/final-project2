package com.example.finalproject.service.impl;

import com.example.finalproject.dto.TeacherRequest;
import com.example.finalproject.dto.TeacherResponse;
import com.example.finalproject.exceptions.NotFoundException;
import com.example.finalproject.mapper.Mapper;
import com.example.finalproject.model.Teacher;
import com.example.finalproject.repo.TeacherRepo;
import com.example.finalproject.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
private final TeacherRepo teacherRepo;

    public TeacherServiceImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<TeacherResponse> getAll() {
        List<TeacherResponse> result = new ArrayList<>();
        for (Teacher teacher : teacherRepo.findAll()) {
            result.add(Mapper.toTeacherResponse(teacher));
        }
        return result;

    }

    @Override
    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with id: " + id + " not found"));
        return Mapper.toTeacherResponse(teacher);
    }

    @Override
    public void delete(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with id " + id + " not found"));
        teacherRepo.delete(teacher);
    }

    @Override
    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setSpecialization(request.getSpecialization());

        return Mapper.toTeacherResponse(teacherRepo.save(teacher));
    }
    }

