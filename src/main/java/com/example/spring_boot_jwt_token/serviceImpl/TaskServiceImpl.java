package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.TaskMapper;
import com.example.spring_boot_jwt_token.dto.request.TaskRequest;
import com.example.spring_boot_jwt_token.dto.response.TaskResponse;
import com.example.spring_boot_jwt_token.entity.Task;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.TaskRepository;
import com.example.spring_boot_jwt_token.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) {
        Task task = taskMapper.mapToEntity(taskRequest);
        return taskMapper.mapToResponse(task);
    }

    @Override
    public List<TaskResponse> getAll() {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            taskResponses.add(taskMapper.mapToResponse(task));
        }
        return taskResponses;
    }

    @Override
    public TaskResponse getById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException("Task with id " + taskId + " is not found."));
        return taskMapper.mapToResponse(task);
    }

    @Override
    public TaskResponse delete(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException("Task with id " + taskId + " doesn't exist."));
        task.setLesson(null);
        taskRepository.delete(task);
        return taskMapper.mapToResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException("Task with id " + taskId + " is ont found."));
        Task task1 = taskMapper.update(task, taskRequest);
        return taskMapper.mapToResponse(task1);
    }

}
