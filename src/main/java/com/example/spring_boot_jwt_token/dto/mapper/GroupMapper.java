package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.CourseRequest;
import com.example.spring_boot_jwt_token.dto.request.GroupRequest;
import com.example.spring_boot_jwt_token.dto.response.GroupResponse;
import com.example.spring_boot_jwt_token.entity.Company;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.entity.Group;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.repository.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupMapper {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupMapper(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    public Group mapToEntity(GroupRequest groupRequest) {
        Course course = courseRepository.findById(groupRequest.getCourseId()).get();
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setImage(groupRequest.getImage());
        group.setCourses(List.of(course));
        course.setGroups(List.of(group));
        return groupRepository.save(group);
    }

    public GroupResponse mapToResponse(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setImage(group.getImage());
        return groupResponse;
    }

    public Group update(Group group, GroupRequest groupRequest) {
        Course course = courseRepository.findById(groupRequest.getCourseId()).get();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setImage(groupRequest.getImage());
        group.setCourses(List.of(course));
        course.setGroups(List.of(group));
        return groupRepository.save(group);
    }
}
