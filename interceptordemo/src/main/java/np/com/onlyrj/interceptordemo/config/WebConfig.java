package np.com.onlyrj.interceptordemo.config;

import np.com.onlyrj.interceptordemo.interceptor.AuthenticationInterceptor;
import np.com.onlyrj.interceptordemo.interceptor.AuthorizationInterception;
import np.com.onlyrj.interceptordemo.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public LoggingInterceptor loggingInterceptor;
    public AuthenticationInterceptor authenticationInterceptor;
    public AuthorizationInterception authorizationInterception;

    public WebConfig(LoggingInterceptor loggingInterceptor,
                     AuthenticationInterceptor authenticationInterceptor,
                     AuthorizationInterception authorizationInterception
                     ){
        this.loggingInterceptor = loggingInterceptor;
        this.authenticationInterceptor = authenticationInterceptor;
        this.authorizationInterception = authorizationInterception;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/login", "/api/public/**")
                .order(1);

        registry.addInterceptor(loggingInterceptor)
                .order(2);
        registry.addInterceptor(authorizationInterception)
                .order(3);

    }

}
