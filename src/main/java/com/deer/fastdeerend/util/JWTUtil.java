package com.deer.fastdeerend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * 自定义封装JWT工具类
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Component
public class JWTUtil {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RedisUtil<String> redisUtil;

    @Value("${fastDeer.jwt.secret}")
    private String secret;

    @Value("${fastDeer.jwt.expireTime}")
    private long expireTime;

    @Value("${fastDeer.jwt.issuer}")
    private String issuer;

    public String createToken(User user) throws JsonProcessingException {
        Date issuerAt = new Date();

        Date expiresAt = new Date(issuerAt.getTime() + 1000 * 60 * expireTime);

        HashMap<String, Object> header = new HashMap<>();

        header.put("alg", "HS256");
        header.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(header)
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer(issuer)
                .withIssuedAt(issuerAt)
                .withExpiresAt(expiresAt)
                .withClaim("userInfo", objectMapper.writeValueAsString(user))
                .sign(Algorithm.HMAC256(secret));

        redisUtil.setTokenWithExpire(token, token, expireTime);
        return token;
    }

    public VerifyTokenResult verifyToken(String jwtToken) {
        VerifyTokenResult.VerifyTokenResultBuilder builder = VerifyTokenResult.builder();
        try {
            if (!redisUtil.hasToken(jwtToken)) {
                builder.status(false).data("token已过期");
                return builder.build();
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
            return builder.status(true).data(decodedJWT.getClaim("userInfo").asString()).build();
        } catch (Exception e) {
            builder.status(false).data("token校验错误");
            return builder.build();
        }
    }
}
