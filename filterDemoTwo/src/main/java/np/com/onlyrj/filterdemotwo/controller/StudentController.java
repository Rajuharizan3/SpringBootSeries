package np.com.onlyrj.filterdemotwo.controller;

import np.com.onlyrj.filterdemotwo.dto.Student;
import np.com.onlyrj.filterdemotwo.dto.StudentResponseDto;
import np.com.onlyrj.filterdemotwo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody Student student){
        StudentResponseDto responseDto = studentService.createStudent(student);
        return ResponseEntity.ok(responseDto);
    }
}
