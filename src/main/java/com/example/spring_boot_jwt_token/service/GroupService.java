package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.GroupRequest;
import com.example.spring_boot_jwt_token.dto.response.GroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    GroupResponse save(GroupRequest groupRequest);

    List<GroupResponse> getAll();

    GroupResponse getById(Long id);

    GroupResponse delete(Long id);

    GroupResponse update(Long id, GroupRequest groupRequest);

    GroupResponse assignGroupToCourse(Long groupId, Long courseId);

    String assignGroup(Long groupId, Long courseId);

}
