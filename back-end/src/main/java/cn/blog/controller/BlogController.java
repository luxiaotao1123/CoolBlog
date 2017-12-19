package cn.blog.controller;


import cn.blog.bean.Blog;
import cn.blog.service.BlogService;
import cn.blog.utils.R1;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "拿到所有博客（分页）",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示个数",required = true,dataType = "Integer")
    })
    @GetMapping(value = "blogs")
    public R1 ajaxBlog(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogService.getALLBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        return R1.add("blog",pageInfo);
    }

    /*@ApiOperation(value = "拿到所有博客（分类）",notes = "在blog/{标签}后面在分类路由")
    @ApiImplicitParam(name = "label",value = "博客标签",required = true,dataType = "String")
    @GetMapping(value = "blogs/{label}")
    public R1 getLableBlogs(@PathVariable("label")String label){
        List<Blog> blogList = blogService.selectBlogsByLabel(label);
        return R1.add("blogbylabel",blogList);
    }*/

    @ApiOperation(value = "拿到所有博客（分类）",notes = "")
    @ApiImplicitParam(name = "label",value = "博客标签",required = true,dataType = "String")
    @PostMapping(value = "label")
    public R1 getLableBlogs(@RequestParam String label){
        List<Blog> blogList = blogService.selectBlogsByLabel(label);
        return R1.add("blogbylabel",blogList);
    }

    @ApiOperation(value = "拿到一篇博客的详情",notes = "")
    @GetMapping(value = "blog/{blogId}")
    public R1 getOneBlog(@PathVariable("blogId")Integer blogId){
        Blog blog = blogService.getOneBlog(blogId);
        return R1.add("getBlog",blog);
    }

    @ApiOperation(value = "遍历日期，以月份为单位",notes = "")
    @GetMapping(value = "months")
    public R1 orderByMonth() {
        return R1.add("Monthss", blogService.orderByMonth());
    }


    @ApiOperation(value = "博客归档",notes = "")
    @GetMapping(value = "archives")
    public R1 getArchives() {
        return R1.add("archives",blogService.getArchives());
    }
}
