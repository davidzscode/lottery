package com.qihang.common.util.jwt;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.qihang.common.vo.BaseVO;
import com.qihang.constant.Constant;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 11:20
 */
public class JWTUtil {
    /**
     * 生成令牌
     *
     * @param userId
     * @return
     */
    public static String createToken(Integer userId, String pwd) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, Constant.JWT_TTL);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);
        dataMap.put("pwd", pwd);
        String token = JWT.create().withHeader(map).withClaim("dataMap", dataMap).withSubject("令牌").withIssuedAt(new Date()).withExpiresAt(expireDate).sign(Algorithm.HMAC256(Constant.SECRET));
        return token;
    }

    public static String createRefreshToken(Integer userId, String pwd) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);
        dataMap.put("pwd", pwd);
        String token = JWT.create().withHeader(map).withClaim("dataMap", dataMap).withSubject("令牌").withIssuedAt(new Date()).sign(Algorithm.HMAC256(Constant.REFRESH_SECRET));
        return token;
    }

    /**
     * 检验token
     *
     * @param token
     * @return
     */
    public static BaseVO verifyToken(String token, String secret) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        verifier.verify(token);
        return new BaseVO();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Map<String, Object> decodeToken(String token, String secret) {
        Map<String, Object> map = new HashMap<>();
        if (verifyToken(token, secret).getSuccess()) {
            DecodedJWT jwt = JWT.decode(token);
            JSONObject js = JSONUtil.parseObj(jwt.getClaim("dataMap").toString());
            map.put("userId", js.getInt("userId"));
            map.put("pwd", js.getStr("pwd"));
            return map;
        }
        return null;
    }
}
