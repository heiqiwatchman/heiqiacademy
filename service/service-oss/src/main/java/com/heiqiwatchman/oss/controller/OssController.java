package com.heiqiwatchman.oss.controller;

import com.heiqiwatchman.commonutils.R;
import com.heiqiwatchman.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hongfengw
 * @create 2022-09-26 14:23
 * @Description:
 * @Version 1.0
 */
@Api(description="阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/ossservice/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @ApiOperation("上传头像文件")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
