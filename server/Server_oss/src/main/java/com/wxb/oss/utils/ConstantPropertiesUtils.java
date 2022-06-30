package com.wxb.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// 当项目已启动  有一个spring 接口 InitializingBean  会执行一个方法

@Component
public class ConstantPropertiesUtils implements InitializingBean {
    // 读取配置文件的值

    // 属性注入
    @Value("${qiniuyun.oss.file.accesskey}")
    private String accesskey;

    @Value("${qiniuyun.oss.file.secretkey}")
    private String secretkey;

    @Value("${qiniuyun.oss.file.bucketname}")
    private String bucketname;

    // 定义一些公开的常用类
    public static String ACCESS_KEY;
    public static String SECRET_KEY;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        // spring 把所有属性初始化后 执行这个方法
        // 依次复制给静态属性
        ACCESS_KEY = accesskey;
        SECRET_KEY = secretkey;
        BUCKET_NAME = bucketname;

    }
}
