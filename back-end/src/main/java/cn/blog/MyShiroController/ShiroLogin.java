package cn.blog.MyShiroController;


import cn.blog.utils.R1;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class ShiroLogin {

    @GetMapping("myajaxLogin")
    public String ajaxLogin(@RequestParam String name,@RequestParam String password){
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()==false){
            try {
                //将存有用户名和密码的token存进subject中
                subject.login(token);
            } catch (UnknownAccountException uae){
                System.out.println("没有用户名为"+token.getPrincipal()+"的用户");
            } catch (IncorrectCredentialsException ice){
                System.out.println("用户名为："+token.getPrincipal()+"的用户密码不正确");
            } catch (LockedAccountException lae){
                System.out.println("用户名为："+token.getPrincipal()+"的用户已被冻结");
            } catch (AuthenticationException e){
                System.out.println("未知错误！");
            }
        }
        return "success";
    }
}
