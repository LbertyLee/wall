package com.wall.controller;

import com.wall.common.Result;
import com.wall.domain.ArticleComments;
import com.wall.domain.PhotoComments;
import com.wall.domain.wallPhoto;
import com.wall.system.PhotoCommentsService;
import com.wall.system.wallPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
public class photoController {

    @Autowired
    private wallPhotoService wallPhotoService;

    @Autowired
    private PhotoCommentsService photoCommentsService;

    /**
     * 上传图片
     * @param fileUpload
     * @return
     */
    @PostMapping("/published")
    public Result PublishedPhoto(@RequestParam("avatar") MultipartFile fileUpload,@RequestParam("photoType") String photoType) {
        return wallPhotoService.PublishedPhoto(fileUpload,photoType);
    }

    @PostMapping("/list")
    public Result getImage(@RequestParam(value = "page") Integer page,  wallPhoto wallPhoto){
        return Result.success(wallPhotoService.GetAllPhoto(page,wallPhoto));
    }

    /**
     * 根据照片类型查询文章
     * @param photoType
     */
    @GetMapping("/type")
    public Result targetList(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "photoType") String photoType)
    {
        return Result.success(wallPhotoService.ByTypeGetMessage(page,photoType));
    }

    /**
     * 对文章进行评论
     */
    @GetMapping("/comments")
    public Result comments(@RequestBody PhotoComments photoComments){
        return photoCommentsService.commentsPhoto(photoComments);
    }

    /**
     * 查看评论
     */
    @GetMapping("/comments/list")
    public Result CommentsList(@RequestParam(value = "page") Integer page
            ,@RequestParam(value = "photoId") Integer photoId){
        return Result.success(photoCommentsService.GetAllComments(page,photoId));
    }

    @DeleteMapping("/deleteComment")
    public Result deleteComment(@RequestParam(value = "CommentId") Integer CommentId){
        return photoCommentsService.deleteComment(CommentId);
    }

    /**
     * 点赞
     */
    @GetMapping("/giveLike")
    public Result giveLike(@RequestParam(value = "PhotoId") Integer PhotoId){
        return wallPhotoService.LikeArticle(PhotoId);
    }





}
