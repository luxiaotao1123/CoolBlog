package cn.blog.controller;


import cn.blog.bean.Blog;
import cn.blog.bean.Handle;
import cn.blog.bean.HandleExample;
import cn.blog.dao.HandleMapper;
import cn.blog.dao.TokenMapper;
import cn.blog.service.BlogService;
import cn.blog.utils.R;
import cn.blog.utils.R1;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "admin/")
public class AdminController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private HandleMapper handleMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @ApiOperation(value = "拿到所有操作方式",notes = "")
    @GetMapping(value = "Handles")
    public R1 ajaxHandle(){
        Map<String, Object> map = new LinkedHashMap<String,Object>();
        List<Handle> handleList = handleMapper.selectByExample(new HandleExample());
        map.put("handle",handleList);
        return R1.success(map);
    }

    @ApiOperation(value = "拿到所有博客",notes = "")
    @GetMapping(value = "Blogs")
    public R1 getBlog(){
        Map<String, Object> map = blogService.getALLBlog();
        return R1.success(map);
    }

    @ApiOperation(value = "上传一篇博客",notes = "成功返回201，失败返回500,无权限返回401")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "label",value = "标签分类",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content",value = "文章内容",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userID",value = "用户Id",required = true,dataType = "Integer")
    })
    @PostMapping(value = "Blog")
    public R1 postBlog(@RequestBody String Blog, HttpServletRequest request){
        Blog blog1= JSONObject.parseObject(Blog,Blog.class);
        String token = request.getHeader("token");
        int userId = tokenMapper.finduserIdByToken(token);
        Date now = new Date();
        Blog blog = new Blog(blog1.getTitle(),blog1.getLabel(),blog1.getContent(),now,userId);
        try {
            blogService.saveBlog(blog);
        }catch (Exception e){
            R1.error(500,"服务器内部错误");
        }
        return R1.success(201,"创建博客成功");
    }

}
