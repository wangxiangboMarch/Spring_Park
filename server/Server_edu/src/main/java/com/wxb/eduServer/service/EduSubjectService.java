package com.wxb.eduServer.service;

import com.wxb.eduServer.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxb.eduServer.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wangler
 * @since 2022-07-04
 */
public interface EduSubjectService extends IService<EduSubject> {
    // 添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
