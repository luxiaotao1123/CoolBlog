package cn.blog.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * 获取真实Ip
 */
public class Iptools {

    private static LoadingCache<HttpServletRequest, String> _cache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build(new CacheLoader<HttpServletRequest, String>() {
        @Override
        public String load(HttpServletRequest key) throws Exception {
            return "";
        }
    });

    private static Logger logger = LoggerFactory.getLogger(Iptools.class);

    public static String gainRealIp(HttpServletRequest request) {
        String ipAddress = "";
        try {
            if (request == null) {
                return ipAddress;
            }
            //shiro
            if (request instanceof ShiroHttpServletRequest) {
                request = (HttpServletRequest) ((ShiroHttpServletRequest) request).getRequest();
            }


            ipAddress = _cache.getUnchecked(request);


            if (ipAddress != null && !ipAddress.isEmpty()) {
                return ipAddress;
            }

            //排除本地测试
            if ("127.0.0.1".equals(request.getServerName()) || "localhost".equals(request.getServerName())) {
                ipAddress = "127.0.0.1";
                return ipAddress;
            }


            ipAddress = request.getRemoteAddr();
            if (StringUtils.isEmpty(ipAddress)) {
                ipAddress = request.getRemoteHost();

            } else {
                return ipAddress;
            }

            if (StringUtils.isNotEmpty(ipAddress)) {

                return ipAddress;
            }

            // 获取真实ip,排除代理ip
            ipAddress = request.getHeader("Referer");

            // ipAddress = this.getRequest().getRemoteAddr();
            ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                // 更换niginx代理
                // ipAddress = request.getRemoteAddr();
                ipAddress = request.getHeader("X-Real-IP");
                if (ipAddress != null && (ipAddress.equals("" + "") || ipAddress.endsWith("0:0:0:0:0:0:0:1"))) {
                    // 根据网卡取本机配置的IP

                    // linux下也可以获取本地的ip地址
                    Enumeration<?> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
                    InetAddress ip;
                    while (allNetInterfaces.hasMoreElements()) {
                        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                        Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            ip = addresses.nextElement();
                            if (ip != null && ip instanceof Inet4Address) {
                                // 获取真实的Ip地址
                                ipAddress = ip.getHostAddress();

                            }
                        }
                    }

                }

            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割"***.***.***.***".length()=15
            if (ipAddress != null && ipAddress.length() > 15) {

                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }

        } catch (Exception e) {
            logger.warn("ip{},解析异常", ipAddress, e);
        } finally {
            if (StringUtils.isNotEmpty(ipAddress)) {
                _cache.put(request, ipAddress);
            }
        }
        return ipAddress;

    }

}
