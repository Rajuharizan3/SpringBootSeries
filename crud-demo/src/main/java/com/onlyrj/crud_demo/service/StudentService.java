package com.onlyrj.crud_demo.service;

import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student saveStudent = studentRepository.save(studentReq);
        return saveStudent;
    }



    public Student getStudent(Long id){
        Optional<Student> studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResponse.isPresent()){
            return studentResponse.get();
        }

        return null;
    }



    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;
    }



    public Student updateStudent(Long id, Student studentRequest) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentRequest.getName());
        studentToSave.setEmail(studentRequest.getEmail());
        studentToSave.setAge(studentRequest.getAge());
        studentToSave.setRollNo(studentRequest.getRollNo());
        studentToSave.setSubject(studentRequest.getSubject());

        studentToSave.setDeleted(false);
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

    public List<Student> createBulkStudents(List<Student> students){
        for(Student student: students){
            student.setDeleted(false);
        }
        return studentRepository.saveAll(students);
    }

    public Boolean deleteSoftlyById(Long id) {
        Optional<Student> existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()){
            return false;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }
}
