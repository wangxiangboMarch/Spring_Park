package com.wxb.eduServer.mapper;

import com.wxb.eduServer.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxb.eduServer.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo selectCoursePublishVoById(String id);
}
