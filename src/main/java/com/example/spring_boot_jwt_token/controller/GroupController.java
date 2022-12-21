package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.GroupRequest;
import com.example.spring_boot_jwt_token.dto.response.GroupResponse;
import com.example.spring_boot_jwt_token.serviceImpl.GroupServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/groups")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GroupController {

    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/save")
    public GroupResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.save(groupRequest);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public List<GroupResponse> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public GroupResponse getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public GroupResponse delete(@PathVariable Long id) {
        return groupService.delete(id);
    }

    @PutMapping("/update/{id}")
    public GroupResponse update(@PathVariable Long id,
                                @RequestBody GroupRequest groupRequest) {
        return groupService.update(id, groupRequest);
    }

    @PostMapping("/assignGroupToCourse/{courseId}/{groupId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public GroupResponse assignStudentToGroup(@PathVariable Long courseId,
                                         @PathVariable Long groupId) {
        return groupService.assignGroupToCourse(courseId, groupId);
    }
}
