package com.wall.system;

import com.wall.common.Result;
import com.wall.domain.sysUser;

public interface sysUserService {

    public sysUser selectUserByUserName(String userName);

    public Result RegisteredUser(sysUser sysUser);


    /**
     * 修改个人密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public Result resetPassword(String oldPassword, String newPassword);

    /**
     * 修改个人信息
     * @param sysUser 用户实体
     */
    public Result updateProfile(sysUser sysUser);

    /**
     * 修改网名
     */
    public Result netName(sysUser sysUser);
}
