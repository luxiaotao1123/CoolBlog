package cn.blog.filter;

import cn.blog.dao.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Order(1)       //过滤顺序，值越小越先执行,好像没有用
//@Component
//@WebFilter(urlPatterns = "/Blogs",filterName = "blosTest")
public class TestFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("过滤器实现");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
