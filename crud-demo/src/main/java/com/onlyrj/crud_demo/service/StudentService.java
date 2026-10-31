package com.onlyrj.crud_demo.service;

import com.onlyrj.crud_demo.dto.CreateStudentRequestDto;
import com.onlyrj.crud_demo.dto.CreateStudentResponseDto;
import com.onlyrj.crud_demo.dto.UpdateStudentRequestDto;
import com.onlyrj.crud_demo.dto.UpdateStudentResponseDto;
import com.onlyrj.crud_demo.entity.Student;
import com.onlyrj.crud_demo.exception.DuplicateExceptionHandler;
import com.onlyrj.crud_demo.exception.ResourceNotFoundException;
import com.onlyrj.crud_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public CreateStudentResponseDto createStudent(CreateStudentRequestDto createStudentRequestDto) {
        Student student = mapToEntity(createStudentRequestDto);

        if(existEmail(student)){
            throw new DuplicateExceptionHandler("Student with email " + student.getEmail() + " already exist");
        }
        Student studentResponse = studentRepository.save(student);

        return mapToDto(studentResponse);
    }



    public CreateStudentResponseDto getStudent(Long id){
        Student studentResponse = studentRepository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        return mapToDto(studentResponse);
    }



    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }



    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentRequest) {
        Student existingStudent = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id " + id + " not found"));

        existingStudent.setName(studentRequest.getName());
        existingStudent.setAge(studentRequest.getAge());
        existingStudent.setRollNo(studentRequest.getRollNo());
        existingStudent.setSubject(studentRequest.getSubject());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        existingStudent.setDeleted(false);
        Student updatedStudent =  studentRepository.save(existingStudent);

        return mapToUpdateDto(updatedStudent);
    }

    public void deleteById(Long id) {

        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        studentRepository.delete(studentToBeDeleted);
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

    public void deleteSoftlyById(Long id) {
        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);
    }


    private Student mapToEntity(CreateStudentRequestDto createStudentRequestDto){
        Student student = new Student();

        student.setName(createStudentRequestDto.getName());
        student.setEmail(createStudentRequestDto.getEmail());
        student.setSubject(createStudentRequestDto.getSubject());
        student.setAge(createStudentRequestDto.getAge());
        student.setRollNo(createStudentRequestDto.getRollNo());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());


        student.setDeleted(false);

        return student;
    }


    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto createStudentResponseDto = new CreateStudentResponseDto();

        createStudentResponseDto.setId(student.getId());
        createStudentResponseDto.setName(student.getName());
        createStudentResponseDto.setEmail(student.getEmail());
        createStudentResponseDto.setSubject(student.getSubject());
        createStudentResponseDto.setRollNo(student.getRollNo());
        createStudentResponseDto.setAge(student.getAge());
        createStudentResponseDto.setMessage("Student Saved successfully");
        createStudentResponseDto.setCreatedAt(student.getCreatedAt());
        createStudentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return createStudentResponseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto updateStudentResponseDto = new UpdateStudentResponseDto();

        updateStudentResponseDto.setId(student.getId());
        updateStudentResponseDto.setName(student.getName());
        updateStudentResponseDto.setEmail(student.getEmail());
        updateStudentResponseDto.setSubject(student.getSubject());
        updateStudentResponseDto.setRollNo(student.getRollNo());
        updateStudentResponseDto.setAge(student.getAge());
        updateStudentResponseDto.setMessage("Student updated successfully");
        updateStudentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return updateStudentResponseDto;
    }

    private boolean existEmail(Student student){
        return studentRepository.existsByEmail(student.getEmail()); // we cannot use email to search a record because it takes lots of time to search the record.
    }
}
