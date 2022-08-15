package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.common.ServletUtils;
import com.wall.domain.ArticleComments;
import com.wall.domain.LoginUser;
import com.wall.domain.PhotoComments;
import com.wall.mapper.PhotoCommentsMapper;
import com.wall.mapper.wallPhotoMapper;
import com.wall.system.PhotoCommentsService;
import com.wall.utils.Paging;
import com.wall.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PhotoCommentsServiceImpl implements PhotoCommentsService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PhotoCommentsMapper photoCommentsMapper;

    @Autowired
    private wallPhotoMapper  wallPhotoMapper;

    @Autowired
    private Paging paging;

    /**
     * 评论照片
     * @param photoComments
     * @return
     */
    @Override
    public Result commentsPhoto(PhotoComments photoComments) {
        LoginUser loginUser=tokenUtils.getLoginUser(ServletUtils.getRequest());
        photoComments.setCommentUser(loginUser.getSysUser().getUserName());
        int a=photoCommentsMapper.insertPhotoComments(photoComments);
        if(a!=1){
            return Result.error("评论失败");
        }
        //增加文章表:wall_photo中的评论数量
        Integer Id=photoComments.getPhotoId();
        wallPhotoMapper.UpdatePhoto(Id);
        return  Result.success("评论成功");
    }

    @Override
    public Map<String, Object> GetAllComments(Integer page, Integer photoId) {
        paging.setStartPage(page);
        List<ArticleComments> CommentsList =photoCommentsMapper.selectCommentsByPhotoId(photoId);
        return paging.Paging(CommentsList);
    }

    @Override
    public Result deleteComment(Integer Id) {
        LoginUser loginUser=tokenUtils.getLoginUser(ServletUtils.getRequest());
        String userName=loginUser.getSysUser().getUserName();
        if(photoCommentsMapper.selectComments(Id,userName)==null
                && !Objects.equals(photoCommentsMapper.GetPhotoUser(Id), loginUser.getSysUser().getUserId())
                && !Objects.equals(loginUser.getSysUser().getUserType(), "admin")
        ){
            return Result.error("没有权限");
        }
        //对文章的总评论数进行减一
        wallPhotoMapper.UpdatePhotoComments(Id);
        photoCommentsMapper.deleteCommentById(Id);
        return Result.success("删除成功");
    }
}
