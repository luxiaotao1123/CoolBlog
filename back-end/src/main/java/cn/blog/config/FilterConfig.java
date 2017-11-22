package cn.blog.config;

import cn.blog.filter.ConfigFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Autowired
    private ConfigFilter configFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(configFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("configFilter");
        return filterRegistrationBean;
    }
}
