package np.com.onlyrj.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler) throws Exception{

        String apiKey = servletRequest.getHeader("x-api-key");

        if(apiKey != null && !apiKey.equals("password123")){
            return false;
        }

        return true;
    }

}
