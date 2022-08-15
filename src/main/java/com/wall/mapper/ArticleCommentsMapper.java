package com.wall.mapper;


import com.wall.domain.ArticleComments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleCommentsMapper {

    int insertArticleComments(ArticleComments articleComments);

    List<ArticleComments> selectCommentsByArticleId(Integer articleId);

    int deleteCommentById(Integer Id);

    ArticleComments selectComments(@Param("Id") Integer Id,@Param("CommentUser") String CommentUser);

    Long GetArticleUser(Integer Id);


}