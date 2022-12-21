package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.InstructorMapper;
import com.example.spring_boot_jwt_token.dto.request.InstructorRequest;
import com.example.spring_boot_jwt_token.dto.response.InstructorResponse;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.entity.Instructor;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.repository.InstructorRepository;
import com.example.spring_boot_jwt_token.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final InstructorMapper instructorMapper;

    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                 CourseRepository courseRepository,
                                 InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.instructorMapper = instructorMapper;
    }


    @Override
    public InstructorResponse save(InstructorRequest instructorRequest) {
        Instructor instructor = instructorMapper.mapToEntity(instructorRequest);
        return instructorMapper.mapToResponse(instructor);
    }

    @Override
    public InstructorResponse assignInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException(String.format("Instructor with id %s is not found.", instructorId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id %s is not found.", courseId)));
        instructor.setCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return instructorMapper.mapToResponse(instructor);
    }

    @Override
    public String assignInstructor(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException(String.format("Instructor with id %s is not found.", instructorId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id %s is not found.", courseId)));
        instructor.setCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return String.format("Instructor with id %s is assigned to course.", instructorId);
    }

    @Override
    public List<InstructorResponse> getAll() {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (Instructor instructor : instructorRepository.findAll()) {
            instructorResponses.add(instructorMapper.mapToResponse(instructor));
        }
        return instructorResponses;
    }

    @Override
    public InstructorResponse getById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Instructor with id " + id + " is not found."));
        return instructorMapper.mapToResponse(instructor);
    }

    @Override
    public InstructorResponse delete(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Instructor with id " + id + " is not found."));
        instructorRepository.delete(instructor);
        return instructorMapper.mapToResponse(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorMapper.update(instructor, instructorRequest);
        instructorRepository.save(instructor);
        return instructorMapper.mapToResponse(instructor);
    }
}
