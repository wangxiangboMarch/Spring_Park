package com.wxb.eduServer.controller;

import com.wxb.utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduServer/user")
@CrossOrigin // 跨域解决
public class EduLoginController {


    // login
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    // info
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "admin")
                .data("name","admin")
                .data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png");
    }
}
