package com.wall.system.Impl;

import com.wall.domain.LoginUser;
import com.wall.domain.sysUser;
import com.wall.exception.UserPasswordNotMatchException;
import com.wall.system.LonginService;
import com.wall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LonginServiceImpl implements LonginService {

    @Autowired
     private TokenUtils tokenUtils;

    @Resource
    private AuthenticationManager authenticationManager;


    @Override
    public String login(String username, String password) throws UserPasswordNotMatchException{
        // 用户验证
        Authentication authentication = null;
        try{
//             该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (Exception e){
            throw new UserPasswordNotMatchException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenUtils.createToken(loginUser);
    }
}
