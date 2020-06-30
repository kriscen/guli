package com.kris.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.kris.serviceoss.service.OssService;
import com.kris.serviceoss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.ENDPOINT;
        String accessKeyId = ConstantPropertiesUtils.KEYID;
        String accessKeySecret = ConstantPropertiesUtils.KEYSECRET;
        String bucketName = ConstantPropertiesUtils.BUCKETNAME;

        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid + filename;

            /*
                根据日期分类
             */
            String dateStr = new DateTime().toString("yyyy/MM/dd");

            filename = dateStr + "/" + filename;
            //使用oss上传文件
            //bucket名称   上传到文件路径和文件名称  输入流
            ossClient.putObject(bucketName,filename, inputStream);

            ossClient.shutdown();
            //根据阿里云命名规则
            String url = "https://"+bucketName+"."+endPoint+"/"+filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return uploadUrl;
        }
    }
}
