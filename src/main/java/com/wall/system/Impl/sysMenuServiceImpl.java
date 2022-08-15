package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.domain.sysMenu;
import com.wall.mapper.sysMenuMapper;
import com.wall.system.sysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class sysMenuServiceImpl implements sysMenuService {

    @Autowired
    private sysMenuMapper sysMenuMapper;


    @Override
    public Result GetUserInfo() {
        Map<String,Object> result=new HashMap<>();
        List<sysMenu> menus=GetMeuName();
        result.put("menus",menus);
        return Result.success(result);
    }

    /**
     * 获取菜单目录
     * @return
     */
    public List<sysMenu> GetMeuName(){
        return sysMenuMapper.selectMenuByParentId();
    }
}
