package com.wall.controller;

import com.wall.common.Result;
import com.wall.common.StringUtils;
import com.wall.domain.sysUser;
import com.wall.system.sysUserService;
import com.wall.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private sysUserService sysUserService;


    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoSavePath; //图标图片存储路径

    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath; //图标映射路径

    /**
     * 更新个人信息
     * @param sysUser
     * @return
     */
    @PutMapping
    public Result updateProfile(@Validated @RequestBody sysUser sysUser) {
        sysUserService.updateProfile(sysUser);
        return Result.success();
    }

    @PutMapping("/resetPassword")
    public Result resetPassword(String oldPassword, String newPassword){
        if(StringUtils.isNull(oldPassword) || StringUtils.isNull(newPassword)){
            return Result.error("密码输入不合法");
        }
        return sysUserService.resetPassword(oldPassword,newPassword);
    }

    @PutMapping("/netname")
    public Result netName(@RequestBody sysUser sysUser){
        return sysUserService.netName(sysUser);
    }


//    /**
//     * 头像上传
//     * @param fileUpload
//     * @return
//     */
//    @PostMapping("/avatar")
//    public  Result avatar(@RequestParam("avatar") MultipartFile fileUpload){
//        //获取文件名
//        String fileName = fileUpload.getOriginalFilename();
//        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //为了避免发生图片替换，这里使用了文件名重新生成
//        fileName = UUID.randomUUID()+suffixName;
//        try {
//            //将图片保存到文件夹里
//            fileUpload.transferTo(new File(ProfilePhotoSavePath+fileName));
//            return Result.success(ProfilePhotoMapperPath+fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error("");
//        }
//    }
}
