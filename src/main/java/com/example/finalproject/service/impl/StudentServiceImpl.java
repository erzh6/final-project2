package com.example.finalproject.service.impl;

import com.example.finalproject.dto.StudentRequest;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.exceptions.NotFoundException;
import com.example.finalproject.mapper.Mapper;
import com.example.finalproject.model.Student;
import com.example.finalproject.repo.CourseRepo;
import com.example.finalproject.repo.EnrollmentRepo;
import com.example.finalproject.repo.StudentRepo;
import com.example.finalproject.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
private final StudentRepo studentRepo;
private final CourseRepo courseRepo;
private final EnrollmentRepo enrollmentRepo;


    public StudentServiceImpl(StudentRepo studentRepo, CourseRepo courseRepo, EnrollmentRepo enrollmentRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }
    @Override
    public Page<StudentResponse> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return studentRepo.findAll(pageable).map(Mapper::toStudentResponse);
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = findStudent(id);
        return Mapper.toStudentResponse(student);
    }

    @Override
    public List<StudentResponse> search(String name) {
        return studentRepo.searchByName(name)
                .stream()
                .map(Mapper::toStudentResponse)
                .toList();
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        //сделать проверку
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        return Mapper.toStudentResponse(studentRepo.save(student));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = findStudent(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        //сделать проверку
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        return Mapper.toStudentResponse(studentRepo.save(student));
    }

    @Override
    public void delete(Long id) {
        Student student = findStudent(id);
        studentRepo.delete(student);
    }

    private Student findStudent(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Student with id: " + id + "Not Found"));
    }
}

