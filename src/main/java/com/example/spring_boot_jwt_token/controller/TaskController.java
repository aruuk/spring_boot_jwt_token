package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.TaskRequest;
import com.example.spring_boot_jwt_token.dto.response.TaskResponse;
import com.example.spring_boot_jwt_token.serviceImpl.TaskServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/tasks")
@PreAuthorize("hasAuthority('ADMIN')")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping("/saveTask")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest) {
        return taskServiceImpl.saveTask(taskRequest);
    }

    @GetMapping("/getAllTasks")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public List<TaskResponse> getTasks() {
        return taskServiceImpl.getAll();
    }

    @GetMapping("/getTask/{taskId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public TaskResponse getById(@PathVariable Long taskId) {
        return taskServiceImpl.getById(taskId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @DeleteMapping("/deleteTask/{taskId}")
    public TaskResponse deleteTask(@PathVariable Long taskId) {
        return taskServiceImpl.delete(taskId);
    }

    @PutMapping("/updateTask/{taskId}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public TaskResponse updateTaskById(@PathVariable Long taskId,
                                       @RequestBody TaskRequest taskRequest) {
        return taskServiceImpl.updateTask(taskId, taskRequest);
    }

}