package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.TaskRequest;
import com.example.spring_boot_jwt_token.dto.response.TaskResponse;
import com.example.spring_boot_jwt_token.entity.Lesson;
import com.example.spring_boot_jwt_token.entity.Task;
import com.example.spring_boot_jwt_token.repository.LessonRepository;
import com.example.spring_boot_jwt_token.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    public TaskMapper(TaskRepository taskRepository,
                      LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
        this.lessonRepository = lessonRepository;
    }

    public Task mapToEntity(TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(taskRequest.getLessonId()).get();
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setLesson(lesson);
        lesson.setTasks(List.of(task));
        return taskRepository.save(task);
    }

    public TaskResponse mapToResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());
        return taskResponse;
    }

    public Task update(Task task, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(taskRequest.getLessonId()).get();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setLesson(lesson);
        lesson.setTasks(List.of(task));
        return taskRepository.save(task);
    }
}
