package cn.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableCaching
@Controller
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})      //排除内置的MultipartResolver
@MapperScan("cn.blog.dao")
public class CoolBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolBlogApplication.class, args);
	}

}