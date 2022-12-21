package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.StudentRequest;
import com.example.spring_boot_jwt_token.dto.response.StudentResponse;
import com.example.spring_boot_jwt_token.entity.Group;
import com.example.spring_boot_jwt_token.entity.Student;
import com.example.spring_boot_jwt_token.repository.GroupRepository;
import com.example.spring_boot_jwt_token.repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public StudentMapper(GroupRepository groupRepository,
                         StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public Student mapToEntity(StudentRequest studentRequest) {
        Student student = new Student();
        Group group = groupRepository.findById(studentRequest.getGroupId()).get();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGroup(group);
        group.setStudents(List.of(student));
        return studentRepository.save(student);
    }

    public StudentResponse mapToResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }

    public Student update(Student student, StudentRequest studentRequest) {
        Group group = groupRepository.findById(studentRequest.getGroupId()).get();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGroup(group);
        group.setStudents(List.of(student));
        return studentRepository.save(student);
    }
}
