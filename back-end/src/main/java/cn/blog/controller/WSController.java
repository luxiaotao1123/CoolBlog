package cn.blog.controller;


import cn.blog.utils.R1;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSController {

    @MessageMapping("/index")
    @SendTo("/topic/clients")
    public R1 responseVoid(String message){
        return R1.add("msg",message);
    }
}
