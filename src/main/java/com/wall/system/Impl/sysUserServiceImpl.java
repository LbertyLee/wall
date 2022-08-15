package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.common.ServletUtils;
import com.wall.domain.LoginUser;
import com.wall.domain.sysUser;
import com.wall.domain.wallArticle;
import com.wall.mapper.sysUserMapper;
import com.wall.mapper.wallArticleMapper;
import com.wall.system.sysUserService;
import com.wall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class sysUserServiceImpl implements sysUserService {

    @Autowired
    private sysUserMapper sysUserMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private wallArticleMapper  wallArticleMapper;

    @Override
    public sysUser selectUserByUserName(String username) {
        return sysUserMapper.selectUserByUserName(username);
    }

    @Override
    public Result RegisteredUser(sysUser sysUser) {
        sysUser.setUserNetName(sysUser.getUserName());
        sysUser SysUser=sysUserMapper.selectUserByUserName(sysUser.getUserName());
        if(SysUser==null){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            int result= sysUserMapper.insertUser(sysUser);
            if(result!=1){
                return  Result.error(400,"注册失败！！！");
            }
            return Result.success(200,"注册成功,请登录！！！");
        }
        return Result.error(400,"该用户已存在,请重新输入！！！");
    }

    @Override
    public Result resetPassword(String oldPassword, String newPassword) {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(oldPassword,loginUser.getPassword()))
        {
          return  Result.error("旧密码错误!");
        }
        String newEncryptedPassword = encryptPassword(newPassword);
        sysUser User = new sysUser();
        User.setPassword(newEncryptedPassword);
        loginUser.setSysUser(sysUserMapper.selectUserByUserId(loginUser.getSysUser().getUserId()));
        tokenUtils.refreshLoginUser(loginUser);
        tokenUtils.refreshLoginUser(loginUser);
        return Result.success("修改成功");
    }

    @Override
    public Result updateProfile(sysUser sysUser) {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        sysUser setSysUser = loginUser.getSysUser();
        //以下三个属性不允许修改
        sysUser.setUserId(setSysUser.getUserId());
        sysUser.setUserName(setSysUser.getUserName());
        sysUser.setPassword(setSysUser.getPassword());
        sysUser.setAvatar(sysUser.getAvatar());
        //刷新缓存
        loginUser.setSysUser(sysUserMapper.selectUserByUserId(loginUser.getSysUser().getUserId()));
        tokenUtils.refreshLoginUser(loginUser);
        return Result.error("");
    }

    @Override
    public Result netName(sysUser sysUser) {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        sysUser.setUserId(loginUser.getSysUser().getUserId());
        //更新文章表的字段：user_net_name
        Long userId=loginUser.getSysUser().getUserId();
        String userNetName=loginUser.getSysUser().getUserNetName();
        List<wallArticle> wallArticleList =wallArticleMapper.selectWallArticleByUserId(loginUser.getSysUser().getUserId());
        wallArticleMapper.batchNetNameStatus(wallArticleList,userId,userNetName);
        sysUserMapper.updateUserNetName(sysUser);
        return  Result.success("操作成功");
    }



    //生成强哈希密码
    private String encryptPassword(String password){
        if(password.equals("")){
            throw new RuntimeException("密码不能为空");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
