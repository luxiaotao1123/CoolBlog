package cn.blog.service;



import cn.blog.bean.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/11/16.
 */
public interface BlogService {
    public List<Blog> getALLBlog();

    public void saveBlog(Blog blog);

    public Blog getOneBlog(int blogId);

    public void deleteOneBlog(int blogId);

    public Blog updateOneBlog(Blog blog);

    public List<Blog> selectBlogsByLabel(String label);
}
