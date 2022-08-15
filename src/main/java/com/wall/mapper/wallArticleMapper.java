package com.wall.mapper;

import com.wall.domain.wallArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface wallArticleMapper {

    List<wallArticle> selectWallArticle();

    List<Object> ByTypeGetMessage(String articleType);

    int insertWallArticle(wallArticle wallArticle);

    int batchNetNameStatus( @Param("wallArticleList") List<wallArticle> wallArticleList,@Param("userId") Long userId, @Param("userNetName") String userNetName);

    List<wallArticle> selectWallArticleByUserId(Long userId);

    List<wallArticle> selectWallArticleById(Long Id);

    int UpdateArticle(@Param("Id")Integer Id);

    int UpdateArticleLike(Integer articleId);

    int UpdateArticleComments(Integer Id);


}