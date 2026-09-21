package com.example.finalproject.service.impl;

import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.dto.EnrollmentResponse;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.exceptions.NotFoundException;
import com.example.finalproject.mapper.Mapper;
import com.example.finalproject.model.Course;
import com.example.finalproject.model.Enrollment;
import com.example.finalproject.model.Student;
import com.example.finalproject.repo.CourseRepo;
import com.example.finalproject.repo.EnrollmentRepo;
import com.example.finalproject.repo.StudentRepo;
import com.example.finalproject.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public EnrollmentResponse enroll(Long studentId, Long courseId) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        return Mapper.toEnrollmentResponse(enrollmentRepo.save(enrollment));
    }

    @Override
    public void unenroll(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));
        enrollmentRepo.delete(enrollment);
    }

    @Override
    public List<CourseResponse> getStudentCourses(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId)
                .stream()
                .map(Enrollment::getCourse)
                .map(Mapper::toCourseResponse)
                .toList();
        //        findStudent(studentId);
//        List<CourseResponse> result = new ArrayList<>();
//        for (Enrollment enrollment : enrollmentRepo.findByStudentId(studentId)) {
//            result.add(Mapper.toCourseResponse(enrollment.getCourse()));
//        }
//        return result;
    }

    @Override
    public List<StudentResponse> getCourseStudents(Long courseId) {
        return enrollmentRepo.findByCourseId(courseId)
                .stream()
                .map(Enrollment::getStudent)
                .map(Mapper::toStudentResponse)
                .toList();
        //findCourse(courseId);
//        List<StudentResponse> result = new ArrayList<>();
//        for (Enrollment enrollment : enrollmentRepo.findByCourseId(courseId)) {
//            result.add(Mapper.toStudentResponse(enrollment.getStudent()));
//        }
//        return result;
    }

    private Student findStudent(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
    }

    private Course findCourse(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with id " + id + " not found"));
    }
}