package cn.blog.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

//@Aspect
//@Configuration
public class TestAspectj {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Pointcut("execution(* cn.blog.controller..*(..))")
    public void pointcutService(){}

    @Before("pointcutService()")
    public void beforeMethod(){
        redisTemplate.opsForValue().set("name","lxt");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @After("pointcutService()")
    public void afterMethod(){
        redisTemplate.opsForValue().set("name","zst");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
