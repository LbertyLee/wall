package com.wall.mapper;



import com.wall.domain.wallPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface wallPhotoMapper {

    int insertWallPhoto(wallPhoto wallPhoto);

    List<wallPhoto> selectWallPhoto();

    List<wallPhoto> ByTypeGetMessage(String PhotoType);

    int UpdatePhoto(@Param("Id")Integer Id);

    int UpdatePhotoComments(Integer Id);

    int UpdateArticleLike(Integer photoId);
}