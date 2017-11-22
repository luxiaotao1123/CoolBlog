package cn.blog.interceptor;


import cn.blog.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //String token ="288bdac0-19b6-4a19-8807-5bab2e526445";
        String token = request.getHeader("token");
        boolean isSuccess = false;
        //BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        //tokenService= (TokenService) factory.getBean("tokenService");
        if (tokenService.queryByToken(token)!=null){
            System.out.println("token通过，并且给予管理员权限");
            isSuccess = true;
        }  else if (token==null||token.isEmpty()){
            System.out.println("客户端没有给出token信息");
            response.sendError(401,"对不起，您没有权限");
        }  else {
            System.out.println("token无作用");
            response.sendError(401,"对不起，您没有权限");
        }
        return isSuccess;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
