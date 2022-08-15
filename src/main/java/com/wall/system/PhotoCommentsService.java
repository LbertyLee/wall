package com.wall.system;

import com.wall.common.Result;

import com.wall.domain.PhotoComments;

import java.util.Map;

public interface PhotoCommentsService {

    /**
     * 评论文章
     */
    public Result commentsPhoto(PhotoComments photoComments);

    public Map<String,Object> GetAllComments(Integer page, Integer photoId);

    /**
     *
     * @param Id
     * @return
     */
    public Result deleteComment(Integer Id);
}
