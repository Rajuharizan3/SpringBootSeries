package np.com.onlyrj.interceptordemo.config;

import np.com.onlyrj.interceptordemo.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public LoggingInterceptor loggingInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor){
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptor(InterceptorRegistry interceptorRegistry){
        
    }

}
