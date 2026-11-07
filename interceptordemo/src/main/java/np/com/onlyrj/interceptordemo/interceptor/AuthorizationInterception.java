package np.com.onlyrj.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterception implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Object handler
    )throws Exception{

        String userRole = servletRequest.getHeader("x-user-role");

        if(userRole != null && !userRole.equals("ADMIN")){
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"You are not authorized to perform this action\"\n" +
                            "}"
            );

            return false;
        }

        return true;
    }
}
