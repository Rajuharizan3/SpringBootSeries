package com.onlyrj.crud_demo.repository;

import com.onlyrj.crud_demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    public Student saveStudent(Student studentReq) {
        System.out.println("Inside StudentRepository");
        System.out.println("Exit StudentRepository");

        Student s1 = new Student();
        s1.setName("Raju Harizan");
        s1.setAge(25);
        s1.setEmail("raju1811021826@gmail.com");
        s1.setRollNo(4);
        s1.setSubject("Spring Boot");
        return s1;
    }
}
