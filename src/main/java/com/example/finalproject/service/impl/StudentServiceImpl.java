package com.example.finalproject.service.impl;

import com.example.finalproject.dto.StudentRequest;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.exceptions.BadRequestException;
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
        return Mapper.toStudentResponse(findStudentById(id));
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
        if (studentRepo.existsByEmail(request.getEmail())){
            throw new BadRequestException("Student with that email exists");
        }
        if (studentRepo.existsByPhone(request.getPhone())){
            throw new BadRequestException("Student with that phone exists");
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        return Mapper.toStudentResponse(studentRepo.save(student));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        if(studentRepo.existsByEmail(request.getEmail())){
            throw new BadRequestException("Student with that email exists");
        }
        if (studentRepo.existsByPhone(request.getPhone())){
            throw new BadRequestException("Student with that phone exists");
        }
        Student student = findStudentById(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        return Mapper.toStudentResponse(studentRepo.save(student));
    }

    @Override
    public void delete(Long id) {
        Student student = findStudentById(id);
        studentRepo.delete(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with that id: " + id + "not found"));
    }

//    private Student findStudent(Long id) {
//        return studentRepo.findById(id)
//                .orElseThrow(()-> new NotFoundException("Student with id: " + id + "Not Found"));
    }


