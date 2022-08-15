package com.wall.controller;

import com.wall.common.Result;
import com.wall.domain.LoginBody;
import com.wall.domain.sysUser;
import com.wall.system.LonginService;
import com.wall.system.sysMenuService;
import com.wall.system.sysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LonginService longinService;

    @Autowired
    private sysUserService sysUserService;


    @Autowired
    private sysMenuService  sysMenuService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody){
        Result result=new Result();
        result.put("token",longinService.login(loginBody.getUsername(),loginBody.getPassword()));
        return Result.success("操作成功", result);
    }

    @PostMapping("/registered")
    public  Result registered(@RequestBody sysUser sysUser){
        return sysUserService.RegisteredUser(sysUser);
    }

    @GetMapping("/getuserinfo")
    public Result getUserInfo(){
        return Result.success(sysMenuService.GetUserInfo());
    }


}
