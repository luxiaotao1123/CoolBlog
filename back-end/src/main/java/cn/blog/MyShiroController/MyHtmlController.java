package cn.blog.MyShiroController;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class MyHtmlController {

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/success")
    public String toSuccess(){
        return "success";
    }

    @RequestMapping("/error1")
    public String toError(){
        return "error1";
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin1")
    public String toAdmin(){
        return "admin1";
    }

    @RequiresRoles("user")
    @RequestMapping("/user")
    public String toUser(){
        return "user";
    }
}
