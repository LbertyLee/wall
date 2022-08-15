package com.wall.system.Impl;

import com.wall.domain.LoginUser;
import com.wall.domain.sysUser;
import com.wall.system.sysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private sysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        sysUser sysUser = sysUserService.selectUserByUserName(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(sysUser sysUser){
        return new LoginUser(sysUser);
    }
}
