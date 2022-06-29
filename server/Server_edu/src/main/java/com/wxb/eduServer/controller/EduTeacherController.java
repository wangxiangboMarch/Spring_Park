package com.wxb.eduServer.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxb.eduServer.entity.EduTeacher;
import com.wxb.eduServer.entity.vo.TeacherQuery;
import com.wxb.eduServer.service.EduTeacherService;
import com.wxb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wangler
 * @since 2022-06-22
 */
@RestController
@RequestMapping("/eduServer/teacher")
@CrossOrigin
public class EduTeacherController {

    // 注入Service
    @Autowired
    private EduTeacherService eduTeacherService;

    // 讲师表的所有数据
    // rest风格
    @GetMapping("/findAllTeachers")
    public R findAllTeachers() {

        List<EduTeacher> teachers = eduTeacherService.list(null);
        return R.ok().data("items", teachers);
    }

    // 逻辑删除讲师
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id){

        if (eduTeacherService.removeById(id)) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    // 根据id获取教师信息
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);

        if (teacher != null) {
            return R.ok().data("item", teacher);
        }else {
            return R.error();
        }
    }

    // 分页查询讲师
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {

        Page<EduTeacher> page = new Page<>(current, limit);
        // 实现分页
        eduTeacherService.page(page, null);

        return R.ok().data("items", page.getRecords());
    }

    // 分页查询讲师—— - 条件查询
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  // 把对应的json数据封装到对象中 tip 需要post  required = false 可以为空
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> page = new Page<>(current, limit);
        // 创建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // StringUtils springboot 中的
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            // 大于等于
            wrapper.gt("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            // 小于等于
            wrapper.le("gmt_create", end);
        }

        wrapper.orderByDesc("gmt_modified");

        // 实现分页
        eduTeacherService.page(page, wrapper);

        return R.ok().data("items", page.getRecords()).data("total", page.getTotal());
    }

    // 添加讲师
    @PostMapping(value = "addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        if (eduTeacherService.save(eduTeacher)) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    // 讲师的修改
    @PostMapping(value = "updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {

        if (eduTeacherService.updateById(eduTeacher)) {
            return R.ok();
        }else {
            return R.error();
        }
    }

}

