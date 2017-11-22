package cn.blog.service;



import cn.blog.bean.Blog;

import java.util.Map;

/**
 * Created by liuwen on 2017/11/16.
 */
public interface BlogService {
    public Map<String,Object> getALLBlog();

    public void saveBlog(Blog blog);
}
