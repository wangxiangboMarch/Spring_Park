package com.wxb.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface OssService  {

    // 上传文件到OSS  单文件
    String upload(MultipartFile file);
}
