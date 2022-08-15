package com.wall.system;

import com.wall.common.Result;
import com.wall.domain.wallArticle;

import java.util.Map;


public interface wallArticleService {


    public Map<String,Object> GetAllMessage(Integer page, wallArticle wallArticle);

    /**
     * 根据文章类型查询文章
     */
    public Map<String,Object> ByTypeGetMessage(Integer page,String articleType);

    /**
     * 发布文章
     */
    public Result PublishedArticles(wallArticle wallArticle);

}
