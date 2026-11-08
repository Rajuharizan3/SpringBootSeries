package np.com.onlyrj.aopintroductiondemo.service;

import np.com.onlyrj.aopintroductiondemo.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//@Primary
public class LoggingDecorator implements StudentService{

    private final StudentServiceImpl studentServiceImpl;

    public LoggingDecorator(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public void createStudent(Student student) {
        // Logging related logics

        LoggingServiceUtils.logStart(
                "StudentServiceImpl", "createStudent");

        studentServiceImpl.createStudent(student);

        LoggingServiceUtils.logEnd(
                "StudentServiceImpl", "createStudent");

    }
}
