package cn.blog.controller;

import cn.blog.utils.R1;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuwen on 2017/11/22.
 */
@ControllerAdvice
public class TestController1 {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R1 test(){
        return R1.error(500,"服务器内部错误");
    }

}
