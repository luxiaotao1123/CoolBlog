package cn.blog.filter;

import cn.blog.dao.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter implements Filter {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //String token = request.getHeader("token");
        String token = "aa37d4d8-dccc-4916-9ff9-ef49aaa4beef";
        boolean isFilter = false;
        if (tokenMapper.findByStringToken(token)!=null){
            System.out.println("token通过，并且给予管理员权限");
            isFilter = true;
        }else if (token==null||token.isEmpty()){
            System.out.printf("客户端没有给出token信息");
        }  else {
            System.out.println("token无效");
        }
        if (isFilter){
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
