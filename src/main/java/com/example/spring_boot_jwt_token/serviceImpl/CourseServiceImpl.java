package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.CourseMapper;
import com.example.spring_boot_jwt_token.dto.request.CourseRequest;
import com.example.spring_boot_jwt_token.dto.response.CourseResponse;
import com.example.spring_boot_jwt_token.dto.views.CourseResponseView;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository,
                             CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Course course = courseMapper.mapToEntity(courseRequest);
        return courseMapper.mapToResponse(course);
    }

    @Override
    public List<CourseResponse> getAll() {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            courseResponses.add(courseMapper.mapToResponse(course));
        }
        return courseResponses;
    }

    @Override
    public CourseResponse getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with id " + id + " not found")));
        return courseMapper.mapToResponse(course);
    }

    @Override
    public CourseResponse delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " not found"));
        courseRepository.delete(course);
        return new CourseResponse();
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " is not found"));
        Course course1 = courseMapper.update(course, courseRequest);
        return courseMapper.mapToResponse(course1);
    }

    private List<CourseResponse> getAllCoursesPagination2(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course :courses) {
            courseResponses.add(courseMapper.mapToResponse(course));
        }
        return courseResponses;
    }

    private List<Course> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return courseRepository.searchByCourseName(text.toUpperCase(Locale.ROOT),pageable);
    }

    public CourseResponseView getAllCoursesPagination(String text, int page , int size) {
        CourseResponseView courseResponseView = new CourseResponseView();
        Pageable pageable = PageRequest.of(page - 1 , size, Sort.by("courseName"));
        courseResponseView.setCourseResponses(getAllCoursesPagination2(search(text,pageable)));
        List<CourseResponse> courseResponses = new ArrayList<>();
        Page<Course> allCourses = courseRepository.findAll(pageable);
        for (Course course : allCourses) {
            courseResponses.add(courseMapper.mapToResponse(course));
        }
        courseResponseView.setCourseResponses(courseResponses);
        courseResponseView.setCurrentPage(pageable.getPageNumber() + 1);
        courseResponseView.setTotalPages(allCourses.getTotalPages());
        return courseResponseView;
    }
}
