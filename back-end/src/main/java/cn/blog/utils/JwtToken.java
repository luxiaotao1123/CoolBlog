package cn.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JwtToken {
    public static final int EXPIRE_TIME = 128;
    public static final String HEADER = "token";

    private static final String KEY = "cn.blog";
    public static final String ID = "id";
    private static Logger logger = LoggerFactory.getLogger(JwtToken.class);

    /**
     * 生成token
     *
     * @param id  用户id
     * @return
     */
    public static String toToken(Integer id) {
        id = Optional.ofNullable(id).orElse(-1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, EXPIRE_TIME);
        Map<String, Object> map = new HashMap<>();
        map.put(ID, id);
        return Jwts.
                builder().
                setClaims(map).
                setIssuedAt(new Date()).
                setExpiration(calendar.getTime()).
                signWith(SignatureAlgorithm.HS256, KEY).
                compact();

    }


    /**
     * 解密token
     *
     * @param token token
     * @return
     */
    public static Integer parseToken(String token) {
        Integer id = null;
        if (token == null || token.length() == 0) {
            logger.warn("用户所传token参数为空");
            return null;
        }
        Claims body = null;

        try {
            body = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.warn("用户所传token={},解析异常", token, e);
        }
        if (body == null || body.isEmpty()) {
            logger.warn("用户所传token={}解密失败", token);
            return id;
        }

        Object res = body.get(ID);


        if (res == null) {
            logger.warn("用户所传token={}中不包含{}", token, ID);
            return id;
        }

        if (new Date().after(body.getExpiration())) {
            logger.warn("用户{}的token={}已经失效", res, token);

            return id;
        }
        id = (Integer) res;
        return id;

    }

}
