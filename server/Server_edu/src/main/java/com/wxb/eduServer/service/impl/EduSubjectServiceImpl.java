package com.wxb.eduServer.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxb.eduServer.entity.EduSubject;
import com.wxb.eduServer.entity.excel.SubjectData;
import com.wxb.eduServer.entity.subject.OneSubject;
import com.wxb.eduServer.entity.subject.TwoSubject;
import com.wxb.eduServer.listen.SubjectExcelListener;
import com.wxb.eduServer.mapper.EduSubjectMapper;
import com.wxb.eduServer.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wangler
 * @since 2022-07-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try{

            // 文件数据流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        // 查找出所有的一级subject
        QueryWrapper oneWrapper = new QueryWrapper();
        oneWrapper.eq("parent_id", "0");
        // 利用父类的mapper
//        List<EduSubject> one = baseMapper.selectList(oneWrapper);
        // 利用本类的
        List<EduSubject> one = this.list(oneWrapper);
        // 查找出所有的二级subject
        QueryWrapper twoWrapper = new QueryWrapper();
        twoWrapper.ne("parent_id", "0");
        List<EduSubject> two = this.list(twoWrapper);
        // 组装数据
        // 创建集合
        List<OneSubject> result = new  ArrayList<>();

        for (EduSubject item: one) {
            OneSubject oneSubject = new OneSubject();
            // 把item对象中的属性copy 到一级菜单身上
            BeanUtils.copyProperties(item, oneSubject);

            // 查找对应的二级分类
            List<TwoSubject> twoResult = new  ArrayList<>();
            for (EduSubject t : two) {
                if (t.getParentId().equals(oneSubject.getId())) {
                    //创建二级类别vo对象
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(t, twoSubject);
                    twoResult.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoResult);
            // 放进结果数组
            result.add(oneSubject);
        }
        // 返回最终结果
        return result;
    }
}
