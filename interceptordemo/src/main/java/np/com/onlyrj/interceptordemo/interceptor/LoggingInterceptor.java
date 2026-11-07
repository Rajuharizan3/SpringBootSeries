package np.com.onlyrj.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler) throws Exception{

        System.out.println("--------------------Incoming request-----------------------");
        System.out.println("HTTP Methods: " + servletRequest.getMethod());
        System.out.println("Request URI: " + servletRequest.getRequestURI());
        System.out.println("Request URL: " + servletRequest.getRequestURL());
        System.out.println("Request Parameters: " + servletRequest.getQueryString());
        System.out.println("Client IP: " + servletRequest.getRemoteAddr());
        System.out.println("Token Header: " + servletRequest.getHeader("token"));

        if(handler instanceof HandlerMethod handlerMethod){
            System.out.println("Controller: " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: " + handlerMethod.getMethod().getName());
        }

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler,
            Exception ex) throws Exception{

        System.out.println("Response Status: " + servletResponse.getStatus());
    }
}
