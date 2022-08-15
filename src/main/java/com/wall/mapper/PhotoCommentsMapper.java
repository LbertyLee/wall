package com.wall.mapper;



import com.wall.domain.ArticleComments;
import com.wall.domain.PhotoComments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PhotoCommentsMapper {

    int insertPhotoComments(PhotoComments photoComments);

    List<ArticleComments> selectCommentsByPhotoId(Integer PhotoId);

    PhotoComments selectComments(@Param("Id") Integer Id, @Param("CommentUser") String CommentUser);

    Long GetPhotoUser(Integer Id);

    int deleteCommentById(Integer Id);

}