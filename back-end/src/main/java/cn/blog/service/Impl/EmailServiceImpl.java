package cn.blog.service.Impl;

import cn.blog.bean.User;
import cn.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean senEmail(User user,String email) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.163.com");
            mailSender.setUsername("t1341870251@163.com");
            mailSender.setPassword("xltys1995");

            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"GBK");
            helper.setFrom(mailSender.getUsername());
            helper.setTo(email);
            helper.setSubject("title");
            helper.setText("邮件发送成功");
            String emailToken = getEmailToken(user);
            String url = "<a href='http://localhost:8088/activateMail?emailToken="+emailToken+"'>激活"+"</a></br><h1>如果以上超连接无法访问，请将以下网址复制到浏览器地址栏中</h1><h2>http://localhost:8088/activateMail?emailToken="+emailToken+"</h2>";
            helper.setText(url,true);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getEmailToken(User user){
        String token = UUID.randomUUID().toString();
        String value = user.toString();
        redisTemplate.opsForValue().set(token,value);
        redisTemplate.expire(token,60, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public boolean balanceToken(String emailToken) {
        if(redisTemplate.opsForValue().get(emailToken)!=null){
            return true;
        }
        return false;
    }

}
