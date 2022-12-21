package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.InstructorRequest;
import com.example.spring_boot_jwt_token.dto.response.InstructorResponse;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.entity.Instructor;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.repository.InstructorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstructorMapper {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    public InstructorMapper(InstructorRepository instructorRepository,
                            CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    public Instructor mapToEntity(InstructorRequest instructorRequest) {
        Course course = courseRepository.findById(instructorRequest.getCourseId()).get();
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructor.setCourse(course);
        course.setInstructors(List.of(instructor));
        return instructorRepository.save(instructor);
    }

    public InstructorResponse mapToResponse(Instructor instructor) {
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(instructor.getId());
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        return instructorResponse;
    }

    public Instructor update(Instructor instructor, InstructorRequest instructorRequest){
        Course course = courseRepository.findById(instructorRequest.getCourseId()).get();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructor.setCourse(course);
        course.setInstructors(List.of(instructor));
        return instructorRepository.save(instructor);
    }

}

