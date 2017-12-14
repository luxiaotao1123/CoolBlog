package cn.blog.service;


import cn.blog.bean.User;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {


    boolean senEmail(User user,String email);

    public String getEmailToken(User user);

    public boolean balanceToken(String emailToken);
}
