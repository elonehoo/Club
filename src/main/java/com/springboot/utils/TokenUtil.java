package com.springboot.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springboot.beans.Student;
​import java.util.Date;

public class TokenUtil {
​
    private static final long EXPIRE_TIME= 10*60*60*1000;   //token有效期
    private static final String TOKEN_SECRET="hcy";             //密钥盐
​
    /**
     * 获取token
     * @param username  用户名
     * @return
     */
    public static String getToken(String username){
        String token = null;
        try {
            // 生成时间戳
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    // 设置自定义字段
                    .withClaim("username", username)
                    // 时间戳
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    ​
    /**
     * 签名生成
     * @param student
     * @return
     */
    public static String sign(Student student){
        // 获取token
        return getToken(student.getMemberName());
    }
​
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            // 验证JWT
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username:  " + jwt.getClaim("username").asString());
            System.out.println("过期时间：   " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
