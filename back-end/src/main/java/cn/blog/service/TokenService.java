package cn.blog.service;

import cn.blog.bean.User;

import java.util.Map;


public interface TokenService {

    public Map<String,Object> createToken(User user);

    public String queryByToken(String token);

    public void checkExpire();
}
