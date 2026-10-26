package com.onlyrj.crud_demo.service;

import com.onlyrj.crud_demo.dto.CreateStudentRequestDto;
import com.onlyrj.crud_demo.dto.CreateStudentResponseDto;
import com.onlyrj.crud_demo.dto.UpdateStudentRequestDto;
import com.onlyrj.crud_demo.dto.UpdateStudentResponseDto;
import com.onlyrj.crud_demo.entity.Student;
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

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student studentResponse = studentRepository.save(student);

        return mapToDto(studentResponse);
    }



    public CreateStudentResponseDto getStudent(Long id){
        Optional<Student> studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResponse.isPresent()){
            return mapToDto(studentResponse.get());
        }

        return null;
    }



    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }



    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentRequest) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentRequest.getName());
        studentToSave.setAge(studentRequest.getAge());
        studentToSave.setRollNo(studentRequest.getRollNo());
        studentToSave.setSubject(studentRequest.getSubject());
        studentToSave.setUpdatedAt(LocalDateTime.now());

        studentToSave.setDeleted(false);
        Student updatedStudent =  studentRepository.save(studentToSave);

        return mapToUpdateDto(updatedStudent);
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


    private Student mapToEntity(CreateStudentRequestDto createStudentRequestDto){
        Student student = new Student();

        student.setName(createStudentRequestDto.getName());
        student.setEmail(createStudentRequestDto.getEmail());
        student.setSubject(createStudentRequestDto.getSubject());
        student.setAge(createStudentRequestDto.getAge());
        student.setRollNo(createStudentRequestDto.getRollNo());

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
}
