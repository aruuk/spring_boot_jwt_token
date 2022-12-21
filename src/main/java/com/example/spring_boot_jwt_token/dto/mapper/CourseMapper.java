package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.CourseRequest;
import com.example.spring_boot_jwt_token.dto.response.CourseResponse;
import com.example.spring_boot_jwt_token.entity.Company;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.CompanyRepository;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    public CourseMapper(CompanyRepository companyRepository, CourseRepository courseRepository) {
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
    }

    public Course mapToEntity(CourseRequest courseRequest) {
        Company company = companyRepository.findById(courseRequest.getCompanyId()).get();
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        course.setCompany(company);
        company.setCourses(List.of(course));
        return courseRepository.save(course);
    }

    public CourseResponse mapToResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setDescription(course.getDescription());
        return courseResponse;
    }

    public Course update(Course course, CourseRequest courseRequest) {
        Company company = companyRepository.findById(courseRequest.getCompanyId()).get();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        course.setCompany(company);
        company.setCourses(List.of(course));
        return courseRepository.save(course);
    }
}
