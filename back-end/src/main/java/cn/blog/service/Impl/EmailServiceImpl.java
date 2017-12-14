package cn.blog.service.Impl;

import cn.blog.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

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
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}
