package com.heiqiwatchman.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.heiqiwatchman.oss.service.OssService;
import com.heiqiwatchman.oss.utils.ConstantPropertiesUtils;
import com.heiqiwatchman.servicebase.exception.HeiqiException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author hongfengw
 * @create 2022-09-26 14:24
 * @Description:
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        //获取文件名称
        String filename = file.getOriginalFilename();

        //由于阿里云OSS对于相同文件名称会进行覆盖,所以我们要对文件名称进行处理:前面加上一个随机数
        //1.在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename = uuid+filename;
        //2.把文件按照日期进行分类
        //2022/2/20/a.jpg
        String datePath = new DateTime().toString("yyyy/MM/dd");
        filename = datePath+ "/" +filename;

        try {
            InputStream inputStream = file.getInputStream();
            // 发送PutObject请求,进行文件上传
            //bucketName: Bucket名称
            //filename: 上传到OSS的文件路径和文件名称 如 /aa/bb/1.jpg
            //inputStream: 文件输入流
            ossClient.putObject(bucketName, filename, inputStream);

            String url = "https://"+bucketName+"."+endPoint+"/"+filename;
            return url;//上传成功,返回url
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            //关闭OSSClient
            ossClient.shutdown();
        }
    }

}
