package cn.blog.service;


import cn.blog.bean.User;

public interface EmailService {

    boolean senEmail(String email);

    public String getEmailToken(User user);
}
