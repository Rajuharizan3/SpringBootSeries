package np.com.onlyrj.filtersDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        System.out.println("Http Request: "
                + httpRequest.getMethod()
                + httpRequest.getRequestURI()
        );


        System.out.println("Http Response: "
                + httpResponse.getStatus()
        );



        String token = httpRequest.getHeader("token");
        String apiKey = httpRequest.getHeader("x-api-key");

        if(token == null || !token.equals("12345")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Invalid or missing token\"\n" +
                            "}"
            );
            return;
        }

        if(apiKey == null || !apiKey.equals("secret123")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Invalid or missing api key\"\n" +
                            "}"
            );

            return;

        }

        filterChain.doFilter(httpRequest, httpResponse);

    }
}
