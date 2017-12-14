package cn.blog.controller;

import cn.blog.bean.User;
import cn.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "myajaxRegister",method = {RequestMethod.GET})
    @ResponseBody
    public String sendEmail(@RequestParam String email){
        User user = new User("luxiaotao","0331");
        new Thread(){
            @Override
            public void run(){
                emailService.senEmail(user,email);
            }
        }.start();
        return "邮件已发送至您的邮箱，请激活";
    }

    @RequestMapping(value = "activateMail",method = {RequestMethod.GET})
    public String activateMail(@RequestParam String emailToken){
        if (emailService.balanceToken(emailToken)){
            return "success";
        }
        return "error1";
    }

}
