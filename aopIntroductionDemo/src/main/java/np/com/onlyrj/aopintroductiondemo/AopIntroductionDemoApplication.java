package np.com.onlyrj.aopintroductiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopIntroductionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopIntroductionDemoApplication.class, args);
        System.out.println("Server is running...");
    }

}
