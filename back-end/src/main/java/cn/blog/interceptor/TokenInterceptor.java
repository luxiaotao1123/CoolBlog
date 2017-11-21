package cn.blog.interceptor;

import cn.blog.dao.TokenMapper;
import cn.blog.service.TokenService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token ="288bdac0-19b6-4a19-8807-5bab2e526445";
        //String token = request.getHeader("token");
        boolean isSuccess = false;
        //BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        //tokenService= (TokenService) factory.getBean("tokenService");
        if (tokenService.queryByToken(token)!=null){
            System.out.println("token通过，并且给予管理员权限");
            isSuccess = true;
        }  else if (token==null|token.isEmpty()){
            System.out.printf("客户端没有给出token信息");
        }  else {
            System.out.println("token无效");
        }
        return isSuccess;
    }

}
