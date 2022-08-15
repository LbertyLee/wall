package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.common.ServletUtils;
import com.wall.domain.ArticleComments;
import com.wall.domain.LoginUser;
import com.wall.mapper.ArticleCommentsMapper;
import com.wall.mapper.wallArticleMapper;
import com.wall.system.ArticleCommentsService;
import com.wall.utils.Paging;
import com.wall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service

public class ArticleCommentsServiceImpl implements ArticleCommentsService {

    @Autowired
    private ArticleCommentsMapper articleCommentsMapper;

    @Autowired
    private wallArticleMapper wallArticleMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private Paging paging;




    @Override
    public Result commentsArticle(ArticleComments articleComments){
        LoginUser loginUser=tokenUtils.getLoginUser(ServletUtils.getRequest());
        articleComments.setCommentUser(loginUser.getSysUser().getUserName());
        int a=articleCommentsMapper.insertArticleComments(articleComments);
        if(a!=1){
            return Result.error("评论失败");
        }
        //增加文章表:wall_article中的评论数量
        Integer Id=articleComments.getArticleId();
        wallArticleMapper.UpdateArticle(Id);
        return  Result.success("评论成功");
    }

    @Override
    public Map<String, Object> GetAllComments(Integer page, Integer ArticleId) {
        paging.setStartPage(page);
        List<ArticleComments> CommentsList =articleCommentsMapper.selectCommentsByArticleId(ArticleId);
        return paging.Paging(CommentsList);
    }


    @Override
    public Result deleteComment(Integer Id) {
        //判断是否有权限删除该评论
        LoginUser loginUser=tokenUtils.getLoginUser(ServletUtils.getRequest());
        String userName=loginUser.getSysUser().getUserName();
        if(articleCommentsMapper.selectComments(Id,userName)==null
            && !Objects.equals(articleCommentsMapper.GetArticleUser(Id), loginUser.getSysUser().getUserId())
            && !Objects.equals(loginUser.getSysUser().getUserType(), "admin")
        ){
            return Result.error("没有权限");
        }
        //对文章的总评论数进行减一
        wallArticleMapper.UpdateArticleComments(Id);
        articleCommentsMapper.deleteCommentById(Id);
        return Result.success("删除成功");
    }

    @Override
    public Result LikeArticle(Integer articleId) {
        if(wallArticleMapper.UpdateArticleLike(articleId)!=1){
         return Result.error("点赞失败");
        }
        return Result.success("点赞成功");
    }

}
