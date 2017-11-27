package cn.blog.test;

import cn.blog.bean.Blog;
import cn.blog.bean.Handle;
import cn.blog.bean.HandleExample;
import cn.blog.dao.BlogMapper;
import cn.blog.dao.HandleMapper;
import cn.blog.dao.TokenMapper;
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
}
