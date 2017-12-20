package cn.blog.utils;

import cn.blog.bean.*;
import cn.blog.dao.BlogMapper;
import cn.blog.dao.HandleMapper;
import cn.blog.dao.TokenMapper;
import cn.blog.dao.UserMapper;
import cn.blog.service.BlogService;
import cn.blog.service.CategoryService;
import cn.blog.service.EmailService;
import cn.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private HandleMapper handleMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void Test2(){
        List<Handle> list = handleMapper.selectByExample(new HandleExample());
        for (Handle handle:list){
            System.out.println(handle.getHandle());
        }
    }

    @Test
    public void Test3(){
        String token = null;
                //"a72c97dd-79d6-44f2-9ddf-d12a847949f";
        if (tokenMapper.findByStringToken(token)!=null){
            System.out.println("token通过，并且给予管理员权限");
        }  else if (token==null||token.isEmpty()){
            System.out.printf("客户端没有给出token信息");
        }else {
            System.out.println("token无效");
        }
    }

    @Test
    public void Test4(){
        String token = "e53d4a4a-19c8-4227-8064-2ffa417e2dd6";
        int userId = tokenMapper.finduserIdByToken(token);
        System.out.println(userId);
    }

    @Test
    public void Test5(){
        List<Blog> list = blogMapper.selectBlogsByLabel("Python");
        System.out.println(list.size());
    }

    @Test
    public void Test6(){
        Integer[] blogIds = {56};
        blogMapper.updateStatus(blogIds);
    }

    @Test
    public void Test7(){
        List<Blog> blogList = blogMapper.getDustbinBlogs();
        System.out.println(blogList.get(0).getBlogid());
    }

    @RequiresRoles("admin")
    private void testRoles(){
        Assert.assertEquals(true,true);
        System.out.println("admin");
    }

    @Test
    public void test9(){
        List<String> months = blogMapper.queryMonths();
        for (String month:months){
            List<Archive> archives = blogMapper.queryArchives(month);
            for (Archive archive:archives){
                System.out.println(archive.getTitle());
            }
        }
    }

    @Test
    public void test10(){
        System.out.println("#############################################################################");
        Map map = new LinkedHashMap();
        List<String> listMonth = blogMapper.queryMonths();
        for (String month : listMonth){
            List<Archive> listArchive = blogMapper.queryArchives(month);
            map.put(month,listArchive);
        }
        System.out.println("#############################################################################");
    }

    @Test
    public void test11(){
        Map map2 = new LinkedHashMap();
        List<String> listYear = blogMapper.queryYears();
        for (String year:listYear){
            List<String> listMonth = blogMapper.queryMonthsByYear(year);
            List list = new ArrayList();
            for (String month:listMonth){
                Map map1 = new LinkedHashMap();
                String yearMonth = year+"-"+month;
                List<Archive> listArchive = blogMapper.queryArchives(yearMonth);
                map1.put(month,listArchive);
                list.add(map1);
            }
            map2.put(year,list);
        }
    }

    @Autowired
    EmailService emailService;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test12(){
        String token = emailService.getEmailToken(new User("luxiaotao","0331"));
        System.out.println(redisTemplate.opsForValue().get(token));
    }

    @Autowired
    CategoryService categoryService;

    @Test
    public void test13(){
        System.out.println(categoryService.getCategory().size());
    }

    @Test
    public void test14(){
        System.out.println(blogService.getBlogsByKeywords("拉屎").size());
    }

    @Test
    public void test15(){
        R r = new R();
        r.put("key1","11");
        r.put("key2","22");
        System.out.println(r);
    }
}
