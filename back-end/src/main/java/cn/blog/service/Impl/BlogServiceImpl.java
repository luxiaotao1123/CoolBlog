package cn.blog.service.Impl;

import cn.blog.bean.Blog;
import cn.blog.bean.BlogExample;
import cn.blog.dao.BlogMapper;
import cn.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Map<String,Object> getALLBlog() {
        Map<String, Object> map = new LinkedHashMap<String,Object>();
        List<Blog> blogList = blogMapper.selectByExample(new BlogExample());
        map.put("blog",blogList);
        return map;
    }

    @Override
    public void saveBlog(Blog blog) {
        blogMapper.insert(blog);
    }

    @Override
    public Blog getOneBlog(int blogId) {
        return blogMapper.queryByblogId(blogId);
    }

    @Override
    public void deleteOneBlog(int blogId) {
        blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public Blog updateOneBlog(Blog blog) {
        try {
            blogMapper.updateByBlog(blog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return blogMapper.queryByblogId(blog.getBlogid());
    }


}
