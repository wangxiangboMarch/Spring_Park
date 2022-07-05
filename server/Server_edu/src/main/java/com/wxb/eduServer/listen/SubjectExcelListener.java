package com.wxb.eduServer.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxb.eduServer.entity.EduSubject;
import com.wxb.eduServer.entity.excel.SubjectData;
import com.wxb.eduServer.service.EduSubjectService;
import com.wxb.servicebase.handle.GuliException;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为 SubjectExcelListener 不能交给spring来管理 需要自己new 不能注入其他对象
    // 不能实现数据库的操作

    public EduSubjectService eduSubjectService;

    // 手写构造方法
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    // 读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.println("表头" + headMap);
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        // 判断是否有值
        if (subjectData == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        // 一行行读取excel 内容

        // 一级不能重复添加
        EduSubject one = existOneSubject(subjectData.getOneSubjectName());
        if (one == null) {
            // 表示没有 就添加
            one = new EduSubject();
            one.setParentId("0");
            one.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(one);
        }

        // 二级不可以重复添加
        EduSubject two = existTwoSubject(subjectData.getTwoSubjectName(), one.getId());
        if (two == null) {
            two = new EduSubject();
            two.setTitle(subjectData.getTwoSubjectName());
            two.setParentId(one.getId());
            eduSubjectService.save(two);
        }

        System.out.println("****" + subjectData);
    }


    // 一级不能重复添加
    private EduSubject existOneSubject(String name) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;

    }
    // 二级不可以重复添加
    private EduSubject existTwoSubject(String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }

    // 全部完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
