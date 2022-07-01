package com.wxb.oss.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wxb.oss.service.OssService;
import com.wxb.oss.utils.ConstantPropertiesUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = ConstantPropertiesUtils.ACCESS_KEY;
        String secretKey = ConstantPropertiesUtils.SECRET_KEY;
        String bucket = ConstantPropertiesUtils.BUCKET_NAME;
//默认不指定key的情况下，以文件内容的hash值作为文件名
        // 1. UUID 唯一值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String key = DateFormatUtils.format(new Date(),"yyyy/MM/dd") + "/" + uuid + type;
        try {
            InputStream fileInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return "http://cdn.xiangbo.wang/" + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            //ignore
        }

        return null;
    }
}
