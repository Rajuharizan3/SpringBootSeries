package np.com.onlyrj.filterdemotwo.configuration;

import np.com.onlyrj.filterdemotwo.filters.DummyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<DummyFilter> getDummyFilterBean(){
        FilterRegistrationBean<DummyFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new DummyFilter());
//        registrationBean.setOrder(1);

        registrationBean.addUrlPatterns("/api/*, /admin/*");

        return registrationBean;
    }
}
