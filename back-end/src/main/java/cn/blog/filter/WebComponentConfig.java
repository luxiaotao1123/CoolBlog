package cn.blog.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebComponentConfig {

    @Autowired
    private TokenFilter tokenFilter;

    //过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        List<String> urlPatterns = new ArrayList<String>();

        //TestFilter testFilter = new TestFilter();   //测试过滤器
        //urlPatterns.add("/Blogs");      //指定需要过滤的url
        //filterRegistrationBean.setFilter(testFilter);       //set

        //TokenFilter tokenFilter = new TokenFilter();    //管理员登录页面过滤器
        urlPatterns.add("/admin/Blogs");
        filterRegistrationBean.setFilter(tokenFilter);

        filterRegistrationBean.setUrlPatterns(urlPatterns);     //set
        return filterRegistrationBean;
    }

}
