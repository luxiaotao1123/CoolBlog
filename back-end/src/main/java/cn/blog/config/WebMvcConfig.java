package cn.blog.config;

import cn.blog.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/admin/**");
    }



}
