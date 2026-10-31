package com.onlyrj.crud_demo.controller;

import com.onlyrj.crud_demo.dto.CreateStudentRequestDto;
import com.onlyrj.crud_demo.dto.CreateStudentResponseDto;
import com.onlyrj.crud_demo.dto.UpdateStudentRequestDto;
import com.onlyrj.crud_demo.dto.UpdateStudentResponseDto;
import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.service.StudentService;
import jakarta.validation.Valid;
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


    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto createStudentRequestDto) {

        CreateStudentResponseDto createdStudent =
                studentService.createStudent(createStudentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudentById(@PathVariable Long id){
        CreateStudentResponseDto studentResponse = studentService.getStudent(id);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents(){
        List<CreateStudentResponseDto> studentList = studentService.getAllStudent();

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudentById(
            @PathVariable Long id, @RequestBody UpdateStudentRequestDto studentRequest){

        UpdateStudentResponseDto updatedStudent =
                studentService.updateStudent(id, studentRequest);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRecords(){
        studentService.deleteAllStudent();
    }

    @PostMapping("/bulk-student")
    public ResponseEntity<List<Student>> createBulkStudents
            (@RequestBody List<Student> students){
        List<Student> createStudents = studentService.createBulkStudents(students);
        return new ResponseEntity<>(createStudents, HttpStatus.CREATED);
    }

    @PatchMapping("/delete-softly/{id}")
    public ResponseEntity<String> deleteSoftly(@PathVariable Long id){
        studentService.deleteSoftlyById(id);
        return ResponseEntity.noContent().build();
    }



}
