package com.wall.system;

import com.wall.common.Result;
import com.wall.domain.ArticleComments;


import java.util.Map;

public interface ArticleCommentsService {

    /**
     * 评论文章
     */
    public Result commentsArticle(ArticleComments articleComments);


    public Map<String,Object> GetAllComments(Integer page, Integer articleId);

    /**
     *
     * @param Id
     * @return
     */
    public Result deleteComment(Integer Id);

    public Result LikeArticle(Integer articleId);

}
