package cn.blog.service.Impl;

import cn.blog.bean.User;
import cn.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean senEmail(String email) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.163.com");
            mailSender.setUsername("t1341870251@163.com");
            mailSender.setPassword("xltys1995");

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mailSender.getUsername());
            mailMessage.setTo(email);
            mailMessage.setSubject("title");
            mailMessage.setText("邮件发送成功");
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getEmailToken(User user){
        String token = UUID.randomUUID().toString();
        String value = user.toString()+"success";
        redisTemplate.opsForValue().set(token,value);
        return token;
    }

}
