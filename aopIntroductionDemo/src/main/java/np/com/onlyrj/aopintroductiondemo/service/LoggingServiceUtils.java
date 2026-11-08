package np.com.onlyrj.aopintroductiondemo.service;

public class LoggingServiceUtils {

    public static void logStart(String className, String methodName){
        System.out.println("Executing -> " + className + " " + methodName);
    }

    public static void logEnd(String className, String methodName){
        System.out.println("Ending -> " + className + " " + methodName);
    }
}
