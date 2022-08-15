package com.wall.controller;

import com.wall.common.Result;
import com.wall.domain.sysUser;
import com.wall.system.sysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public sysUserService sysUserService;

    @PostMapping("/update")
    public Result update(@RequestBody sysUser sysUser){
        sysUser.setUserName(sysUser.getUserName());
        return Result.success();
    }
}
