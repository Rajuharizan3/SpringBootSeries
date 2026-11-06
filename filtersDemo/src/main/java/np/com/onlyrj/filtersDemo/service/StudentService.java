package np.com.onlyrj.filtersDemo.service;

import np.com.onlyrj.filtersDemo.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    public void createStudent(Student studentDto){
        System.out.println("Student Created");
        System.out.println(studentDto.getName());
        System.out.println(studentDto.getEmail());

//        try {
//            Thread.sleep(2000);
//        }catch (Exception e){
//        }
    }
}
