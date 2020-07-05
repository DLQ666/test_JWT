package com.qi.jwt.util;

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @program: jwt
 * @description: Jwt工具类
 * @author: Hasee
 * @create: 2020-07-05 11:13
 */
public class JwtUtils {

    //过期时间
    public static final long EXPIRE = 1000 * 30;
    //APP Secret
    public static final String APP_SECRET = "123456";

    public static String genJwt(String id, String nickname, String avatar) {

        //创建builder对象
        JwtBuilder builder = Jwts.builder();

        //第一部分：JWT 头 header
        builder.setHeaderParam("alg", "HS256");//签名算法
        builder.setHeaderParam("typ", "JWT");//令牌类型

        //第二部分：有效载荷 PlayLoad
        //默认字段
        builder.setId("1");//jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        builder.setSubject("qi-user");//令牌的主题
        builder.setIssuedAt(new Date());//jwt的签发时间
        builder.setExpiration(new Date(System.currentTimeMillis() +EXPIRE));//过期时间

        //私有字段
        builder.claim("id",id);
        builder.claim("nickname",nickname);
        builder.claim("avatar",avatar);

        //第三部分：签名哈希
        builder.signWith(SignatureAlgorithm.HS256,APP_SECRET);

        //将三部分连接起来
        String token = builder.compact();

        return token;
    }

    public static Claims checkJwt(String jwtToken){
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
//        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
//        String signature = claimsJws.getSignature();

        return body;
    }
}
