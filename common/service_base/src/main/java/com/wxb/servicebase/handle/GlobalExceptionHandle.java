package com.wxb.servicebase.handle;

import com.wxb.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  全局拦截错误
 * */

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json 数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("500 全局拦截");
    }
}
