package com.example.finalproject.mapper;

import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.dto.EnrollmentResponse;
import com.example.finalproject.dto.StudentResponse;
import com.example.finalproject.dto.TeacherResponse;
import com.example.finalproject.model.Course;
import com.example.finalproject.model.Enrollment;
import com.example.finalproject.model.Student;
import com.example.finalproject.model.Teacher;

public class Mapper {

    public static TeacherResponse toTeacherResponse(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());
        response.setSpecialization(teacher.getSpecialization());
        return response;
    }

    public static CourseResponse toCourseResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setCredits(course.getCredits());
        if (course.getTeacher() != null) {
            response.setTeacherId(course.getTeacher().getId());
            response.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        }
        return response;
    }

    public static StudentResponse toStudentResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setEnrollmentDate(student.getEnrollmentDate());
        return response;
    }

    public static EnrollmentResponse toEnrollmentResponse(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudent().getId());
        response.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        response.setCourseId(enrollment.getCourse().getId());
        response.setCourseTitle(enrollment.getCourse().getTitle());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setGrade(enrollment.getGrade());
        return response;
    }
}
