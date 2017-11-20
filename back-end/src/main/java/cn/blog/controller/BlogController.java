package cn.blog.controller;


import cn.blog.service.BlogService;
import cn.blog.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "拿到所有博客",notes = "")
    @GetMapping(value = "Blogs")
    public R ajaxBlog(){
        Map<String, Object> map = blogService.getALLBlog();
        return R.ok(map);
    }
}
