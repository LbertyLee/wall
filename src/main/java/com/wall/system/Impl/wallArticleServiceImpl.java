package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.common.ServletUtils;
import com.wall.domain.LoginUser;
import com.wall.domain.wallArticle;
import com.wall.mapper.sysUserMapper;
import com.wall.mapper.wallArticleMapper;
import com.wall.system.wallArticleService;
import com.wall.utils.Paging;
import com.wall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class wallArticleServiceImpl implements wallArticleService {

    @Autowired
    private wallArticleMapper wallArticleMapper;


    @Autowired
    private Paging paging;

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查询所有评论
     */
    @Override
    public Map<String,Object> GetAllMessage(Integer page , wallArticle wallArticle) {
        paging.setStartPage(page);
       List<wallArticle> wallArticlesList = wallArticleMapper.selectWallArticle();
        return paging.Paging(wallArticlesList);
    }

    /**
     * 根据文章类型查询
     * @return
     */
    @Override
    public Map<String,Object> ByTypeGetMessage(Integer page,String articleType) {
        paging.setStartPage(page);
        List<Object> wallArticleList=wallArticleMapper.ByTypeGetMessage(articleType);
        return paging.Paging(wallArticleList);
    }

    @Override
    public Result PublishedArticles(wallArticle wallArticle) {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        Long userId=loginUser.getSysUser().getUserId();
        wallArticle.setUserId(userId);
        //获取用户的网名
        String userNetName=loginUser.getSysUser().getUserNetName();
        wallArticle.setUserNetName(userNetName);
        int rows = wallArticleMapper.insertWallArticle(wallArticle);
        if (rows != 1) {
            return Result.error("发布文章失败");
        }
        return Result.success("上传成功");
    }



}
