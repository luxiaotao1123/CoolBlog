package cn.blog;

import cn.blog.filter.TestFilter;
import cn.blog.filter.TokenFilter;
import cn.blog.interceptor.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


//命令行启动（到项目文件夹中）：mvn spring-boot:run		关闭：ctrl+C
@SpringBootApplication
//@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@Controller
@MapperScan("cn.blog.dao")
public class WarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}

	//mvc控制器
	@Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
		//拦截器
		public void addInterceptors(InterceptorRegistry registry){
			registry.addInterceptor(new MyInterceptor())	//指定拦截器类
					.addPathPatterns("/admin/Handles");		//指定该类拦截的url
		}
	}



}
