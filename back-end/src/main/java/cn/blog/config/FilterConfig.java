package cn.blog.config;

import cn.blog.filter.TestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Autowired
    private TestFilter testFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(testFilter);
        filterRegistrationBean.addUrlPatterns("/Blogs");
        filterRegistrationBean.setName("testFilter");
        return filterRegistrationBean;
    }
}
