package cn.blog.controller;

import cn.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "email",method = {RequestMethod.GET})
    public String sendEmail(){
        String email = "1341870251@qq.com";
        if (emailService.senEmail(email)){
            return "success";
        }
        return "error1";
    }

}
