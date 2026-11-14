package np.com.onlyrj.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String createStudent(){
        System.out.println("Student Saved");
//        try {
//            throw new RuntimeException("Some error occurred");
//        }
//        catch (RuntimeException re){}
        return "Student saved";
    }

}
