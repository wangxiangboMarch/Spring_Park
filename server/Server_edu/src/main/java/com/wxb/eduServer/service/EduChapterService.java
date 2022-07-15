package com.wxb.eduServer.service;

import com.wxb.eduServer.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxb.eduServer.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);
}
