package cn.blog.shiro;


import cn.blog.utils.Iptools;
import cn.blog.utils.JwtToken;
import cn.blog.utils.R;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Configuration
public class ShiroConfig {

    private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    private final Map<String, AccessRecord> ipIntercept = new ConcurrentHashMap<>(1000);

    private final int ACCESS_INTERVAL_TIMES = 2000;
    private final int ACCESS_FORBIDDEN_TIMES = 10;

    private final String ERROR = "error";

    /**
     *这是个自定义的认证类，继承子AuthorizingRealm，负责用户的认证和权限处理
     */
    @Bean
    public MyShiroRealm shiroRealm(){
        MyShiroRealm realm = new MyShiroRealm();
        return realm;
    }

    /** 安全管理器
     * 将realm加入securityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //注意是DefaultWebSecurityManager！！！
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /** shiro filter 工厂类
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/logout","logout");
//        filterChainDefinitionMap.put("/myajaxLogin","anon");
//        filterChainDefinitionMap.put("/**","authc");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/success");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("userAuth", new UserAuthFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> chains = new HashMap<>();
        chains.put("/admin/**", "userAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);

        return shiroFilterFactoryBean;

    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    private class UserAuthFilter extends UserFilter {


        @Override
        protected boolean isAccessAllowed(ServletRequest req, ServletResponse response, Object mappedValue) {


            HttpServletRequest request = WebUtils.toHttp(req);


            if (!isAccess(request)) {
                return false;
            }

            String token = request.getHeader("token");


            if (StringUtils.isEmpty(token) ) {
                logger.warn("Ip={}没有token进行访问", Iptools.gainRealIp(request));
                request.setAttribute(ERROR, R.error());
                return false;
            }
            Integer id = JwtToken.parseToken(token);

            if (id == null) {
                logger.warn("token={}解析id不正确", token);
                request.setAttribute("error", "需要重新登录");
                return false;
            }


            //这里进行刷新token
            HttpServletResponse res = (HttpServletResponse) response;

            res.addHeader(MyShiroRealm.headToken, JwtToken.toToken(id));
            request.setAttribute(JwtToken.ID, id);

            return true;

        }

        @Override
        protected boolean onAccessDenied(ServletRequest request,
                                         ServletResponse response) throws Exception {
            logger.error("紧急异常....");
            HttpServletRequest req = WebUtils.toHttp(request);
            HttpServletResponse res = WebUtils.toHttp(response);
            writeForbid(res, req == null ? R.error() : req.getAttribute(ERROR));
            return false;
        }

    }



    private boolean isAccess(HttpServletRequest res) {
        String ip = Iptools.gainRealIp(res);


        if (org.apache.commons.lang3.StringUtils.isEmpty(ip)) {
            logger.warn("空Ip地址正在进行访问......");
            res.setAttribute(ERROR, R.error());
            return false;

        } else {
            AccessRecord accessRecord = ipIntercept.get(ip);

            long currentTime = System.currentTimeMillis();

            if (accessRecord == null) {
                accessRecord = new AccessRecord();
                accessRecord.setAccessTimes(0);
                accessRecord.setFirstAccessTime(currentTime);
            }
            long intervalTime = currentTime - accessRecord.getFirstAccessTime();

            if (intervalTime > ACCESS_INTERVAL_TIMES) {
                accessRecord.setAccessTimes(0);
                accessRecord.setFirstAccessTime(currentTime);
            }

            int count = accessRecord.getAccessTimes();

            accessRecord.setAccessTimes(++count);

            ipIntercept.put(ip, accessRecord);


            if (intervalTime < ACCESS_INTERVAL_TIMES && accessRecord.getAccessTimes() > ACCESS_FORBIDDEN_TIMES) {
                logger.warn("ip{} 时间间隔 {} 毫秒 访问路径={}, {} 次 ,已拦截 ", ip, intervalTime, res.getRequestURI(), accessRecord.getAccessTimes());
                res.setAttribute(ERROR,  R.error());
                return false;

            }

        }
        return true;
    }

    /**
     * 访问记录
     */
    private class AccessRecord {
        /**
         * 第一次访问时间
         */
        private long firstAccessTime;

        /**
         * 访问的次数
         */
        private int accessTimes;

        public long getFirstAccessTime() {
            return firstAccessTime;
        }

        public void setFirstAccessTime(long firstAccessTime) {
            this.firstAccessTime = firstAccessTime;
        }

        public int getAccessTimes() {
            return accessTimes;
        }

        public void setAccessTimes(int accessTimes) {
            this.accessTimes = accessTimes;
        }

        public void setAccessTimes(Integer accessTimes) {
            this.accessTimes = accessTimes;
        }

        @Override
        public String toString() {
            return "AccessRecord{" +
                    "firstAccessTime=" + firstAccessTime +
                    ", accessTimes=" + accessTimes +
                    '}';
        }
    }

    private void writeForbid(HttpServletResponse response, Object tips) {


        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            try (PrintWriter printWriter = response.getWriter()) {

                printWriter.write(JSONObject.toJSONString(tips));
                printWriter.flush();
            }


        } catch (IOException e) {
            logger.warn("获取写禁止数据流异常", e);
        }

    }
}
