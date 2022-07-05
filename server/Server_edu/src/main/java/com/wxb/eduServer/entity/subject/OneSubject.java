package com.wxb.eduServer.entity.subject;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// 一级分类
@Data
public class OneSubject {

    private String id;

    private String title;

    // 一个一级里面会有多个二级
    private List<TwoSubject> children = new ArrayList<>();
}
