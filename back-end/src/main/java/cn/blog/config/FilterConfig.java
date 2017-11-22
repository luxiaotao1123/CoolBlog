package cn.blog.config;

import cn.blog.filter.TestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {


    @Autowired
    private TestFilter testFilter;

    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        List<String> urlPatterns = new ArrayList<String>();

        urlPatterns.add("/Blogs");      //指定需要过滤的url
        filterRegistrationBean.setFilter(testFilter);       //set

        filterRegistrationBean.setUrlPatterns(urlPatterns);     //set
        return filterRegistrationBean;
    }
}
