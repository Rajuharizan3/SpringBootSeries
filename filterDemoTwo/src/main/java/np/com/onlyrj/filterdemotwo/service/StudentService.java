package np.com.onlyrj.filterdemotwo.service;

import np.com.onlyrj.filterdemotwo.dto.Student;
import np.com.onlyrj.filterdemotwo.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDto createStudent(Student student){
        StudentResponseDto responseDto = new
                StudentResponseDto();

        responseDto.setName(student.getName());
        responseDto.setMessage("Student is saved successfully");

        return responseDto;
    }
}
