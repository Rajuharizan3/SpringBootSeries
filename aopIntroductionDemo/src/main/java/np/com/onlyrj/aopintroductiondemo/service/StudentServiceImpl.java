package np.com.onlyrj.aopintroductiondemo.service;

import np.com.onlyrj.aopintroductiondemo.dto.Student;
import np.com.onlyrj.aopintroductiondemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){

        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        studentRepository.save(student);
    }
}
