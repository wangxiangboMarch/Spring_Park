package com.wxb.eduServer.controller;


import com.wxb.eduServer.entity.subject.OneSubject;
import com.wxb.eduServer.service.EduSubjectService;
import com.wxb.eduServer.service.EduTeacherService;
import com.wxb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wangler
 * @since 2022-07-04
 */
@RestController
@CrossOrigin
@RequestMapping("/eduServer/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    // 添加课程分类
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {

        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }
    // 获取课程列表对象 （树形结构）
    @GetMapping("getAllSubject")
    public R getAllSubjects() {
        List<OneSubject> allOneTwoSubject = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list", allOneTwoSubject);
    }
}

