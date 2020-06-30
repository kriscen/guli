package com.kris.serviceoss.controller;

import com.kris.commonutils.R;
import com.kris.serviceoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public R uploadFile(MultipartFile file){
        String url = ossService.uploadAvatar(file);
        return R.ok().data("url",url);
    }

}
