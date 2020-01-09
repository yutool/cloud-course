package com.anko.coursems.utils;

import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.UserCourse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    public static String getToken(UserInfo user, long ttlMillis) {
        long time = System.currentTimeMillis();
        // 生成JWT的时间
        Date date = new Date(time);
        // 指定签名的时候使用的签名算法，也就是header那部分，Jwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        /**
            创建payload的私有声明
            根据特定的业务需要添加，如果要拿这个做验证
            一般是需要和jwt的接收方提前沟通好验证方式的
         */
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getUserId());
        claims.put("user_name", user.getUserName());
        /**
            生成签名的时候使用的秘钥secret,generalKey()本地封装了的，
            一般可以从本地配置文件中读取，切记这个秘钥不能外露哦,
            它就是你服务端的私钥，在任何场景都不应该流露出去。
            一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
            String key = "secretKey"; // 测试
         */
        SecretKey key = generalKey();
        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明
                .setId(user.getUserId())    // 设置jti(JWT ID)：是JWT的唯一标识，设置一个不重复值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(date)          // iat: jwt的签发时间
                .setSubject("{\"userId\": "+user.getUserId()+"+}")    // sub(Subject)：代表这个JWT的主体，这个是一个json格式的字符串，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);     // 设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis > 0) {
            long expMillis = time + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }

    public static Claims parseToken(String token) throws Exception {
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private static SecretKey generalKey(){
        // String stringKey = Constant.JWT_SECRET; // 本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        String stringKey = "7786df7fc3a34e26a61c034d5ec8245d";
        byte[] encodedKey = Base64.decodeBase64(stringKey); // 本地的密码解码[B@152f6e2
        // System.out.println(encodedKey);//[B@152f6e2
        // System.out.println(Base64.encodeBase64URLSafeString(encodedKey));// 7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

}
