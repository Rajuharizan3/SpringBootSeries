package com.onlyrj.crud_demo.service;

import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student createStudent(Student studentReq) {
        return studentRepository.save(studentReq);
    }



    public Student getStudent(Long id){
        Optional<Student> studentResponse = studentRepository.findById(id);

        if(studentResponse.isPresent()){
            return studentResponse.get();
        }

        return null;
    }



    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }



    public Student updateStudent(Long id, Student studentRequest) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentRequest.getName());
        studentToSave.setEmail(studentRequest.getEmail());
        studentToSave.setAge(studentRequest.getAge());
        studentToSave.setRollNo(studentRequest.getRollNo());
        studentToSave.setSubject(studentRequest.getSubject());

        return studentRepository.save(studentToSave);

    }

    public Boolean deleteById(Long id) {

        Boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) return false;

        studentRepository.deleteById(id);

        return true;
    }

    public void deleteAllStudent() {
        studentRepository.deleteAll();
    }
}
