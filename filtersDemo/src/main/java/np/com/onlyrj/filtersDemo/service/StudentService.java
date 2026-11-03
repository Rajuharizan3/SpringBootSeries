package np.com.onlyrj.filtersDemo.service;

import np.com.onlyrj.filtersDemo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public void createStudent(Student studentDto){
        System.out.println("Student Created");
        System.out.println(studentDto.getName());
        System.out.println(studentDto.getEmail());

//        try {
//            Thread.sleep(2000);
//        }catch (Exception e){
//            log.error("Error is : ", e);
//        }
    }
}
