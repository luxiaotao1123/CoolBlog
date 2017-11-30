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


//命令行启动（到项目文件夹中）：mvn spring-boot:run		关闭(POST)：/admin/shutdown/shutdown
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement		//声明式事务
@EnableCaching      //声明式缓存
@Controller
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})      //排除内置的MultipartResolver
@MapperScan("cn.blog.dao")
public class WarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}

}