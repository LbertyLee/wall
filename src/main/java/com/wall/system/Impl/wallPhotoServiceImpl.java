package com.wall.system.Impl;

import com.wall.common.Result;
import com.wall.common.ServletUtils;
import com.wall.domain.sysUser;
import com.wall.domain.wallArticle;
import com.wall.domain.wallPhoto;
import com.wall.mapper.wallPhotoMapper;
import com.wall.system.wallPhotoService;
import com.wall.utils.Paging;
import com.wall.utils.TokenUtils;
import com.wall.utils.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;
import java.util.Map;


@Service
public class wallPhotoServiceImpl implements wallPhotoService {


    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoSavePath; //图标图片存储路径

    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath; //图标映射路径

    @Autowired
    private wallPhotoMapper  wallPhotoMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private Paging paging;

    @Override
    public Result PublishedPhoto(MultipartFile fileUpload,String photoType) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID()+suffixName;
        try {
            String url=ProfilePhotoMapperPath+fileName;
            if(insertWallPhoto(url,photoType)==1){
                //将图片保存到文件夹里
                fileUpload.transferTo(new File(ProfilePhotoSavePath+fileName));
                return Result.success("上传成功",url);
            }
            return Result.error("上传失败!!!");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传失败!!!");
        }
    }


    @Override
    public Map<String, Object> GetAllPhoto(Integer page, wallPhoto wallPhoto) {
        paging.setStartPage(page);
        List<wallPhoto> wallPhotoList = wallPhotoMapper.selectWallPhoto();
         return paging.Paging(wallPhotoList);
    }

    @Override
    public Map<String, Object> ByTypeGetMessage(Integer page, String photoType) {
        paging.setStartPage(page);
        List<wallPhoto> wallPhotoList=wallPhotoMapper.ByTypeGetMessage(photoType);
        return paging.Paging(wallPhotoList);
    }

    @Override
    public Result LikeArticle(Integer PhotoId) {
        if(wallPhotoMapper.UpdateArticleLike(PhotoId)!=1){
            return Result.error("点赞失败");
        }
        return Result.success("点赞成功");
    }

    /**
     * 将图片地址存到数据库
     * @param photo
     * @return
     */
    public int insertWallPhoto(String photo,String photoType){
        sysUser sysUser=tokenUtils.getLoginUser(ServletUtils.getRequest()).getSysUser();
        wallPhoto wallPhoto=new wallPhoto();
        wallPhoto.setPhoto(photo);
        wallPhoto.setUserNetName(sysUser.getUserNetName());
        wallPhoto.setUserId(sysUser.getUserId());
        wallPhoto.setPhotoType(photoType);
        return wallPhotoMapper.insertWallPhoto(wallPhoto);
    }




}
