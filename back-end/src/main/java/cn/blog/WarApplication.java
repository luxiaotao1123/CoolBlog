package cn.blog;

import cn.blog.filter.TestFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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



}