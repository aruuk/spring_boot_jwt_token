package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.TaskRequest;
import com.example.spring_boot_jwt_token.dto.response.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    TaskResponse saveTask(TaskRequest taskRequest);

    List<TaskResponse> getAll();

    TaskResponse getById(Long id);

    TaskResponse delete(Long id);

    TaskResponse updateTask(Long taskId, TaskRequest taskRequest);

}
