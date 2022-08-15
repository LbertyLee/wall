package com.wall.mapper;

import com.wall.domain.sysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface sysUserMapper {

    sysUser selectUserByUserName(String username);

    int  insertUser(sysUser sysUser);

    sysUser selectUserByUserId(Long id);

    int updateUserNetName(sysUser sysUser);

}