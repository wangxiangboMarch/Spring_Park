package com.wxb.eduServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxb.eduServer.entity.EduVideo;
import com.wxb.eduServer.mapper.EduVideoMapper;
import com.wxb.eduServer.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }
}
