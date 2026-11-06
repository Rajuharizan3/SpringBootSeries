package np.com.onlyrj.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler) throws Exception{

        System.out.println("preHandler called...");

        return true;
    }


    @Override
    public void postHandle(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler,
            ModelAndView modelAndView) throws Exception{

        System.out.println("postHandler called...");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler,
            Exception exception) throws Exception{

        System.out.println("After Completion called...");
    }
}
