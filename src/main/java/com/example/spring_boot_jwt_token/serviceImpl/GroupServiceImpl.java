package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.GroupMapper;
import com.example.spring_boot_jwt_token.dto.request.GroupRequest;
import com.example.spring_boot_jwt_token.dto.response.GroupResponse;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.entity.Group;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.repository.GroupRepository;
import com.example.spring_boot_jwt_token.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CourseRepository courseRepository;

    public GroupServiceImpl(GroupRepository groupRepository,
                            GroupMapper groupMapper,
                            CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.courseRepository = courseRepository;
    }


    @Override
    public GroupResponse save(GroupRequest groupRequest) {
        Group group = groupMapper.mapToEntity(groupRequest);
        return groupMapper.mapToResponse(group);
    }

    @Override
    public List<GroupResponse> getAll() {
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            groupResponses.add(groupMapper.mapToResponse(group));
        }
        return groupResponses;
    }

    @Override
    public GroupResponse getById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Instructor with id " + id + " is not found."));
        return groupMapper.mapToResponse(group);
    }

    @Override
    public GroupResponse delete(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Group with id " + id + " is not found."));
        groupRepository.delete(group);
        return groupMapper.mapToResponse(group);
    }

    @Override
    public GroupResponse update(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Group with id " + id + " is not found."));
        Group group1 = groupMapper.update(group, groupRequest);
        return groupMapper.mapToResponse(group1);
    }

    @Override
    public GroupResponse assignGroupToCourse(Long groupId, Long courseId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NotFoundException(String.format("Group with id %s is not found.", groupId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id %s is not found.", courseId)));
        group.addCourse(course);
        course.addGroup(group);
        groupRepository.save(group);
        return groupMapper.mapToResponse(group);
    }

    @Override
    public String assignGroup(Long groupId, Long courseId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NotFoundException(String.format("Instructor with id %s is not found.", groupId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id %s is not found.", courseId)));
        group.addCourse(course);
        course.addGroup(group);
        groupRepository.save(group);
        return String.format("Group with id %s is assigned to course.", groupId);
    }
}
