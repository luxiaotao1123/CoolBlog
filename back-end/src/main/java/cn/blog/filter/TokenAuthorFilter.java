package cn.blog.filter;

import javax.servlet.annotation.WebFilter;

import cn.blog.dao.TokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//@Component      //注入到IOC容器
@WebFilter(urlPatterns = "login/*",filterName = "tokenAuthorFilter")        //过滤的url和过滤器名字

public class TokenAuthorFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TokenAuthorFilter.class);

    @Override
    public void destroy() {

    }

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = req.getHeader("token");//header方式
        //验证开始
        boolean isFilter = false;

        String method = ((HttpServletRequest) request).getMethod();
        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        }else{
            if (null == token || token.isEmpty()) {
                System.out.println("用户授权认证没有通过!客户端请求参数中无token信息");
            } else {
                if (tokenMapper.findByStringToken(token)!=null) {
                    System.out.println("用户授权认证通过!");
                    isFilter = true;
                } else {
                    System.out.println("用户授权认证没有通过!客户端请求参数token信息无效");
                }
            }

            if (isFilter) {
                logger.info("token filter过滤ok!");
                chain.doFilter(request, response);
            }
        }


    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
