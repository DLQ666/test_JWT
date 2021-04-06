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
        String token = builder
                //JWT 头 header
                .setHeaderParam("alg", "HS256") //签名算法
                .setHeaderParam("typ", "JWT") //令牌类型

                //第二部分：有效载荷 PlayLoad
                //私有字段
                .claim("id", id)
                .claim("nickname", nickname)
                .claim("avatar", avatar)

                //默认字段
                .setSubject("qi-user") //令牌的主题
                .setIssuer("dlqk8s") //签发者
                .setAudience("dlqk8s") //接收方
                .setIssuedAt(new Date()) //jwt令牌的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //过期时间
                .setNotBefore(new Date(System.currentTimeMillis() + 1000 * 20)) //生效时间
                .setId("1") //jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

                //第三部分：签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)

                //组装jwt字符串---将三部分连接起来
                .compact();

        return token;
    }

    public static Claims checkJwt(String jwtToken) {
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
//        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
//        String signature = claimsJws.getSignature();

        return body;
    }
}
