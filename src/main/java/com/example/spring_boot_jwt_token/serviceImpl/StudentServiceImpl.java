package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.StudentMapper;
import com.example.spring_boot_jwt_token.dto.request.StudentRequest;
import com.example.spring_boot_jwt_token.dto.response.StudentResponse;
import com.example.spring_boot_jwt_token.entity.Group;
import com.example.spring_boot_jwt_token.entity.Student;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.GroupRepository;
import com.example.spring_boot_jwt_token.repository.StudentRepository;
import com.example.spring_boot_jwt_token.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = studentMapper.mapToEntity(studentRequest);
        return studentMapper.mapToResponse(student);
    }

    @Override
    public StudentResponse assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException(String.format("Student with id %s is not found.", studentId)));
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id %s is not found.", groupId)));
        student.setGroup(group);
        group.addStudent(student);
        studentRepository.save(student);
        return new StudentResponse();
    }

    @Override
    public String assignStudent(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException(String.format("Student with id %s is not found.", studentId)));
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NotFoundException(String.format("Group with id %s is not found.", groupId)));
        student.setGroup(group);
        group.addStudent(student);
        studentRepository.save(student);
        return String.format("Student with id %s is assigned to course.", studentId);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentResponses.add(studentMapper.mapToResponse(student));
        }
        return studentResponses;
    }

    @Override
    public StudentResponse getById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Student with id " + studentId + " is not found."));
        return studentMapper.mapToResponse(student);
    }

    @Override
    public StudentResponse delete(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Student with id " + studentId + " is not found."));
        studentRepository.delete(student);
        return studentMapper.mapToResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException(String.format("Student with id %s is not found.", studentId)));
        Student student1 = studentMapper.update(student, studentRequest);
        return studentMapper.mapToResponse(student1);
    }

}
