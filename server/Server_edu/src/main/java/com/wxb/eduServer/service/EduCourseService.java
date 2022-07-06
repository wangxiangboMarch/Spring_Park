package com.wxb.eduServer.service;

import com.wxb.eduServer.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxb.eduServer.entity.vo.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
