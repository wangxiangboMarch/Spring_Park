package com.wxb.servicebase.handle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 有参数的构造方法
@NoArgsConstructor  // 无参的构造方法
public class GuliException extends RuntimeException {

    private Integer code; // 错误码
    private String msg; // 错误信息

}
