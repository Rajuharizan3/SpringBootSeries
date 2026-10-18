package com.onlyrj.crud_demo.service;

import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        System.out.println("Inside StudentService");
        Student studentResp = studentRepository.saveStudent(studentReq);
        System.out.println("Exit StudentService");
        return studentResp;
    }
}
