package cn.blog.utils;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> ExceptionHandle(Exception e){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status",100);
        map.put("msg",e.getMessage());
        return map;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("birthday");
    }

    @ModelAttribute
    public void ModelAtrribute(Model model){
        model.addAttribute("name","lxt");
    }
}
