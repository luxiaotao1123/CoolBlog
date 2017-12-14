package cn.blog.service.Impl;

import cn.blog.bean.Archive;
import cn.blog.bean.Blog;
import cn.blog.bean.BlogExample;
import cn.blog.dao.BlogMapper;
import cn.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getALLBlog() {
        return blogMapper.getAllBlog();
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

    @Override
    public List<Blog> selectBlogsByLabel(String label) {
        return blogMapper.selectBlogsByLabel(label);
    }

    @Override
    public void deleteMoreBlogs(Integer[] blogIds) {
        blogMapper.deleteMoreBlogs(blogIds);
    }

    @Override
    public void updateStatus(Integer[] blogIds) {
        blogMapper.updateStatus(blogIds);
    }

    @Override
    public List<Blog> getDustbin() {
        return blogMapper.getDustbinBlogs();
    }

    @Override
    public void deleteDustbinBlogs(Integer[] blogIds) {
        blogMapper.deleteDustbinBlogs(blogIds);
    }

    @Override
    public List<String> orderByMonth() {
        return blogMapper.queryMonths();
    }

    @Override
    public Map getArchives() {
        Map map2 = new LinkedHashMap();
        List<String> listYear = blogMapper.queryYears();
        for (String year:listYear){
            List<String> listMonth = blogMapper.queryMonthsByYear(year);
            List list1=new ArrayList();
            for (String month:listMonth){
                Map map1 = new LinkedHashMap();
                String yearMonth = year+"-"+month;
                List<Archive> listArchive = blogMapper.queryArchives(yearMonth);
                map1.put(month,listArchive);
                list1.add(map1);
            }
            map2.put(year,list1);
        }
        return map2;
    }


}
