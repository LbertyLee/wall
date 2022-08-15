package com.wall.controller;

import com.wall.common.Result;
import com.wall.domain.ArticleComments;
import com.wall.domain.wallArticle;
import com.wall.system.ArticleCommentsService;
import com.wall.system.wallArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wall")
public class wallController {

    @Autowired
    private wallArticleService wallArticleService;

    @Autowired
    private ArticleCommentsService articleCommentsService;


    /**
     * 发布文章
     */
    @PutMapping("/published")
    public  Result PublishedArticles(@RequestBody wallArticle wallArticle){
        return wallArticleService.PublishedArticles(wallArticle);
    }


    /**
     * 查询所有留言列表
     */
    @GetMapping("/list")
    public  Result GetAllMessage(@RequestParam(value = "page") Integer page,  wallArticle wallArticle)
    {
        return Result.success(wallArticleService.GetAllMessage(page,wallArticle));
    }

    /**
     * 根据文章类型查询文章
     * @param articleType
     */
    @GetMapping("/type")
    public Result targetList(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "articleType") String articleType)
    {
        return Result.success(wallArticleService.ByTypeGetMessage(page,articleType));
    }

    /**
     * 对文章进行评论
     */
    @GetMapping("/comments")
    public Result comments(@RequestBody ArticleComments articleComments){

        return articleCommentsService.commentsArticle(articleComments);
    }
    /**
     * 查看评论
     */
    @GetMapping("/comments/list")
    public Result CommentsList(@RequestParam(value = "page") Integer page
                                ,@RequestParam(value = "articleId") Integer articleId){
        return Result.success(articleCommentsService.GetAllComments(page,articleId));
    }
    /**
     * 删除评论
     */
    @DeleteMapping("/deleteComment")
    public Result deleteComment(@RequestParam(value = "CommentId") Integer CommentId){
        return articleCommentsService.deleteComment(CommentId);
    }

    /**
     * 点赞
     */
    @GetMapping("/giveLike")
    public Result giveLike(@RequestParam(value = "articleId") Integer articleId){

        return articleCommentsService.LikeArticle(articleId);
    }
}
