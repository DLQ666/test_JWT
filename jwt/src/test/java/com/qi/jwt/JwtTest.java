package com.qi.jwt;

import com.qi.jwt.entity.Member;
import com.qi.jwt.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @program: jwt
 * @description: JWT测试用例
 * @author: Hasee
 * @create: 2020-07-05 11:32
 */
public class JwtTest {

    @Test
    public void testGenJwt(){
        Member member = new Member();
        member.setId("100");
        member.setNickname("asd");
        member.setAvatar("qwe.jpg");

        String jwt = JwtUtils.genJwt(member.getId(), member.getNickname(), member.getAvatar());
        System.out.println(jwt);
    }

    @Test
    public void testCheckJwt(){
        Claims claims = JwtUtils.checkJwt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxIiwic3ViIjoicWktdXNlciIsImlhdCI6MTU5MzkyMDg0OCwiZXhwIjoxNTkzOTIwODc4LCJpZCI6IjEwMCIsIm5pY2tuYW1lIjoiYXNkIiwiYXZhdGFyIjoicXdlLmpwZyJ9.gwG6TnUtMnZUvSDCfAGZLtiRYya_FswuNRr_yw-wptg");
        String id = (String) claims.get("id");
        String nickname = (String) claims.get("nickname");
        String avatar = (String) claims.get("avatar");
        System.out.println(id);
        System.out.println(nickname);
        System.out.println(avatar);
    }
}
