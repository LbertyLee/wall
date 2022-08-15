package com.wall.system;

import com.wall.common.Result;

import com.wall.domain.wallPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface wallPhotoService {

    public Result PublishedPhoto(MultipartFile fileUpload,String photoType);


    public Map<String,Object> GetAllPhoto(Integer page, wallPhoto wallPhoto);


    /**
     * 根据文章类型查询文章
     */
    public Map<String,Object> ByTypeGetMessage(Integer page,String photoType);


    public Result LikeArticle(Integer PhotoId);

}
