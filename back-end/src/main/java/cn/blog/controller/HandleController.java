package cn.blog.controller;


import cn.blog.bean.Handle;
import cn.blog.bean.HandleExample;
import cn.blog.dao.HandleMapper;
import cn.blog.service.BlogService;
import cn.blog.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "admin/")
public class HandleController {

    @Autowired
    private HandleMapper handleMapper;

    @ApiOperation(value = "拿到所有操作方式",notes = "")
    @GetMapping(value = "Handles")
    public R ajaxHandle(){
        Map<String, Object> map = new LinkedHashMap<String,Object>();
        List<Handle> handleList = handleMapper.selectByExample(new HandleExample());
        map.put("handle",handleList);
        return R.ok(map);
    }

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "拿到所有博客",notes = "")
    @GetMapping(value = "Blogs")
    public R ajaxBlog(){
        Map<String, Object> map = blogService.getALLBlog();
        return R.ok(map);
    }
}
