package np.com.onlyrj.aopintroductiondemo.service;

import np.com.onlyrj.aopintroductiondemo.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ExecutionTimeService implements StudentService{

    private final LoggingDecorator loggingDecorator;

    public ExecutionTimeService(LoggingDecorator loggingDecorator){
        this.loggingDecorator = loggingDecorator;
    }

    @Override
    public void createStudent(Student student) {
        long startTime = System.currentTimeMillis();

        loggingDecorator.createStudent(student);

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;
        System.out.println("Total time taken -> " + totalTime);

    }
}
