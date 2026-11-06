package np.com.onlyrj.filterdemotwo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
public class ResponseHeaderFilter implements Filter {


    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;

        String requestId = UUID.randomUUID().toString();
        httpServletResponse.setHeader("x-request-id", requestId);

        filterChain.doFilter(servletRequest, servletResponse);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Time taken: " + duration);


    }
}
