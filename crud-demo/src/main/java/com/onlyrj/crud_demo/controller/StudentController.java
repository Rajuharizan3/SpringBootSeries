package com.onlyrj.crud_demo.controller;

import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student studentResponse = studentService.getStudent(id);

        if(studentResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllStudents(Student student){
        List<Student> studentList = studentService.getAllStudent();

        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id,
                                                     @RequestBody Student studentRequest){
        Student updatedStudent =
                studentService.updateStudent(id, studentRequest);

        if(updatedStudent == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteById(id);

        if(!isDeleted){
           return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Student deleted...");
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRecords(){
        studentService.deleteAllStudent();
    }


}
