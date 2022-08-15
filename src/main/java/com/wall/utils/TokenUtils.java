package com.wall.utils;

import com.wall.common.Constants;
import com.wall.domain.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class TokenUtils {
    @Autowired
    private  RedisUtils redisUtils;
    // 令牌密钥
    @Value("${token.secret}")
    private String secret;


    //过期时间
    @Value("${token.expireTime}")
    private int expireTime;

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;


    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    /**
     * 通过请求获取Token令牌
     * @param request 请求实例
     * @return 令牌
     */
    private String getToken(HttpServletRequest request)
    {
        return request.getHeader(header);
    }
    /**
     * 从缓存中取出登录的用户
     * @param request 请求
     * @return 登录的用户实体
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        String token = getToken(request);
        if(token!=null) {
            try {
                Claims claims = getClaims(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.CLAIMS_KEY);
                String userKey = getTokenKey(uuid);
                return redisUtils.getCacheObject(userKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 通过令牌获取Claims信息
     * @param token 令牌
     * @return Claims信息
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * 创建Token令牌
     * @return 令牌
     */
    public String createToken (LoginUser loginUser){
        String uuid = UUIDUtils.generateUUID();
        loginUser.setToken(uuid);
        setLoginUser(uuid,loginUser,30);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.CLAIMS_KEY,uuid);
        return  createToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 刷新登录的用户缓存
     * @param loginUser 登录的用户实体
     */
    public void refreshLoginUser(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String uuid = getTokenKey(loginUser.getToken());
        redisUtils.setCacheObject(getTokenCacheKey(uuid),loginUser,expireTime,TimeUnit.MINUTES);
    }

    /**
     * 将登录的用户写入缓存
     * @param token token
     * @param loginUser 登录的用户
     * @param expireTime 缓存过期时间
     */
    public void setLoginUser(String token, LoginUser loginUser,int expireTime){
        redisUtils.setCacheObject(getTokenCacheKey(token), loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取Token缓存键
     * @param key 原始键
     * @return 缓存键
     */
    public String getTokenCacheKey(String key) {
        return Constants.LOGIN_USER_KEY + key;
    }



    private String getTokenKey(String uuid)
    {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

}
