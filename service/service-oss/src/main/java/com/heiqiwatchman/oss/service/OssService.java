package com.heiqiwatchman.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hongfengw
 * @create 2022-09-26 14:24
 * @Description:
 * @Version 1.0
 */
public interface OssService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
