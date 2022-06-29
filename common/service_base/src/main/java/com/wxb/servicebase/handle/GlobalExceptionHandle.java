package com.wxb.servicebase.handle;

import com.wxb.utils.ExceptionUtil;
import com.wxb.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *  全局拦截错误
 * */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json 数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("500 全局拦截");
    }

    // 特定异常
    @ExceptionHandler(IOException.class)
    @ResponseBody // 返回json 数据
    public R error(IOException e) {
        e.printStackTrace();
        return R.error().message("IO 异常处理 拦截");
    }

    // 自定义异常 -- 返回自定义的code和message
    @ExceptionHandler(GuliException.class)
    @ResponseBody // 返回json 数据
    public R error(GuliException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }

}
