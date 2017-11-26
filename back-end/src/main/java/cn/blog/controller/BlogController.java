package cn.blog.controller;


import cn.blog.bean.Blog;
import cn.blog.service.BlogService;
import cn.blog.utils.R1;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "拿到所有博客",notes = "")
    @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "Integer")
    @GetMapping(value = "blogs")
    public R1 ajaxBlog(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogList = blogService.getALLBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        return R1.add("blog",pageInfo);
    }


    @ApiOperation(value = "拿到一篇博客的详情",notes = "")
    @GetMapping(value = "blog/{blogId}")
    public R1 getOneBlog(@PathVariable("blogId")Integer blogId){
        Blog blog = blogService.getOneBlog(blogId);
        return R1.add("getBlog",blog);
    }
}
