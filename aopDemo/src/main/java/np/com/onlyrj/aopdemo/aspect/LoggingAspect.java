package np.com.onlyrj.aopdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//


    @AfterReturning(
            value = "execution(String np.com.onlyrj.aopdemo.service.StudentService.createStudent())",
            returning = "result")
    public void logAfterReturningMethod(String result){
//        System.out.println("LogAfterReturningMethod called ");

        System.out.println("Target Method returning : " + result);
    }

}
