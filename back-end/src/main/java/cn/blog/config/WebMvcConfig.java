package cn.blog.config;

import cn.blog.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor(new MyInterceptor())	//指定拦截器类
        //        .addPathPatterns("/admin/Handles");		//指定该类拦截的url
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/admin/Handles");
    }

    @Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

}
