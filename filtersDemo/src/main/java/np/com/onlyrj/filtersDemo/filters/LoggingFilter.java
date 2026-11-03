package np.com.onlyrj.filtersDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {

    private final ServletRequest servletRequest;

    public LoggingFilter(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();


        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        String requestId = UUID.randomUUID().toString();


        httpResponse.setHeader("X-Request-ID", requestId);

        System.out.println("Incoming request : "
                + httpRequest.getMethod()
                + httpRequest.getRequestURI());

        try {
            filterChain.doFilter(httpRequest, httpResponse);
        }
        finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Response status: "
                    + httpResponse.getStatus());

            System.out.println("API Response time: " + duration);
        }


    }
}
