package cn.blog.controller;


import cn.blog.bean.Blog;
import cn.blog.service.BlogService;
import cn.blog.utils.R;
import cn.blog.utils.R1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "拿到所有博客",notes = "")
    @GetMapping(value = "blogs")
    public R ajaxBlog(){
        Map<String, Object> map = blogService.getALLBlog();
        return R.ok(map);
    }

    @ApiOperation(value = "拿到一篇博客的详情",notes = "")
    @GetMapping(value = "blog/{blogId}")
    public R1 getOneBlog(@PathVariable("blogId")Integer blogId){
        Blog blog = blogService.getOneBlog(blogId);
        return R1.add("getBlog",blog);
    }
}
