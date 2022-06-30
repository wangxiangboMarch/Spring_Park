package com.wxb.oss.controller;

import com.wxb.oss.service.OssService;
import com.wxb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/eduoss/file")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping()
    public R uploadOss(MultipartFile file) {

        // 上传文件 返回文件的 oss地址
        String url = ossService.upload(file);

        return R.ok().data("url", url);
    }
}
