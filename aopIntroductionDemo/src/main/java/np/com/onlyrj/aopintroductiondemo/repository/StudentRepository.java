package np.com.onlyrj.aopintroductiondemo.repository;

import np.com.onlyrj.aopintroductiondemo.dto.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    public void save(Student student){
        System.out.println("Student Saved");
    }
}
