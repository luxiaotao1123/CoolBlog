package cn.blog.test;

import cn.blog.bean.Blog;
import cn.blog.bean.Handle;
import cn.blog.bean.HandleExample;
import cn.blog.dao.BlogMapper;
import cn.blog.dao.HandleMapper;
import cn.blog.dao.TokenMapper;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class test2 {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private HandleMapper handleMapper;

    @Autowired
    private BlogMapper blogMapper;

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

    @Test
    public void shiroTest1(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //将存有用户名和密码的token存进subject中
            subject.login(token);
        } catch (UnknownAccountException uae){
            System.out.println("没有用户名为"+token.getPrincipal()+"的用户");
        } catch (IncorrectCredentialsException ice){
            System.out.println("用户名为："+token.getPrincipal()+"的用户密码不正确");
        } catch (LockedAccountException lae){
            System.out.println("用户名为："+token.getPrincipal()+"的用户已被冻结");
        } catch (AuthenticationException e){
            System.out.println("未知错误！");
        }
        System.out.println("success");
        testRoles();
        subject.logout();
    }

    @Test
    public void shiroTest2(){

    }

    @RequiresRoles("admin")
    private void testRoles(){
        Assert.assertEquals(true,true);
        System.out.println("admin");
    }
}
