package com.kris.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 上传文件到oss
     * @param file
     * @return
     */
    String uploadAvatar(MultipartFile file);
}
