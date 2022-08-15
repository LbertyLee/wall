package com.wall.mapper;

import com.wall.domain.sysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface sysMenuMapper {

    public List<sysMenu> selectMenuByParentId();


}