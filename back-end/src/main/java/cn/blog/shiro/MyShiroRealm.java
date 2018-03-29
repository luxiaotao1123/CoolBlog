package cn.blog.shiro;

import cn.blog.bean.User;
import cn.blog.dao.UserMapper;
import cn.blog.utils.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class MyShiroRealm extends AuthorizingRealm {

    public static final Map<String, Integer> map = new ConcurrentHashMap<>(16);
    public static final String headToken = "token";

    @Autowired
    private UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String name = principals.toString();
        info.addRole("admin");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = (String) usernamePasswordToken.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = userMapper.selectByNameAndPassword(new User(username,password));
        if (user==null){
            System.out.println("用户名密码错误");
        }
        map.put(username,user.getId());
        return new SimpleAuthenticationInfo(username,password,getName());
    }

    public void load(String userName, String passWord, HttpServletResponse response){
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        SecurityUtils.getSubject().login(token);
        response.addHeader(headToken, JwtToken.toToken(map.get(userName)));
        map.remove(userName);
    }
}
