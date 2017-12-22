package cn.blog.controller;

import cn.blog.service.BlogService;
import cn.blog.service.CategoryService;
import cn.blog.utils.R1;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    @ApiOperation(value = "拿到所有标签",notes = "")
    @RequestMapping(value = "category",method = RequestMethod.GET)
    public R1 categorys(){
        return R1.add("category",categoryService.getCategory());
    }

    @ApiOperation(value = "通过关键字搜索博客")
    @ApiImplicitParam(name = "keywords",value = "关键字",required = true,dataType = "String")
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public R1 search(@RequestBody String keywords){
        return R1.add("blogs",blogService.getBlogsByKeywords(keywords));
    }
}
