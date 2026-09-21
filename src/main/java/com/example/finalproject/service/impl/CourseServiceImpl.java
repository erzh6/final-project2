package com.example.finalproject.service.impl;

import com.example.finalproject.dto.CourseRequest;
import com.example.finalproject.dto.CourseResponse;
import com.example.finalproject.exceptions.NotFoundException;
import com.example.finalproject.mapper.Mapper;
import com.example.finalproject.model.Course;
import com.example.finalproject.model.Teacher;
import com.example.finalproject.repo.CourseRepo;
import com.example.finalproject.repo.TeacherRepo;
import com.example.finalproject.service.CourseService;
import com.example.finalproject.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
private final CourseRepo courseRepo;
private final TeacherRepo teacherRepo;

    public CourseServiceImpl(CourseRepo courseRepo, TeacherService teacherService, TeacherRepo teacherRepo) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
    }


    @Override
    public Page<CourseResponse> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return courseRepo.findAll(pageable).map(Mapper::toCourseResponse);
    }

    @Override
    public CourseResponse getById(Long id) {
        return Mapper.toCourseResponse(findCourseById(id));
    }


    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = findCourseById(id);
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCredits(request.getCredits());
        course.setTeacher(findTeacherOrNull(request.getTeacherId()));

        return com.example.finalproject.mapper.Mapper.toCourseResponse(courseRepo.save(course));
    }

    private Teacher findTeacherOrNull(Long teacherId) {
        if (teacherId == null) {
            return null;
        }
       return teacherRepo.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " not found"));
    }

//    private Course findCourse(Long id) {
//        return courseRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with id " + id + " not found"));
//    }


    @Override
    public void delete(Long id) {
        Course course = findCourseById(id);
        courseRepo.delete(course);
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCredits(request.getCredits());
        course.setTeacher(findTeacherOrNull(request.getTeacherId()));

        return Mapper.toCourseResponse(courseRepo.save(course));
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with that id not found"));
    }
}
