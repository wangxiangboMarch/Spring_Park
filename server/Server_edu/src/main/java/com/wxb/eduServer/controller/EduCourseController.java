package com.wxb.eduServer.controller;


import com.wxb.eduServer.entity.vo.CourseInfoForm;
import com.wxb.eduServer.entity.vo.CoursePublishVo;
import com.wxb.eduServer.service.EduCourseService;
import com.wxb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
@Api(description="课程管理")
@RestController
@RequestMapping("/eduServer/course")
@CrossOrigin
public class EduCourseController {


    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public R saveCourseInfo(@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
        @RequestBody CourseInfoForm courseInfoForm){
        // 返回课程id 为了后面添加课程大纲时候需要
        String courseId = courseService.saveCourseInfo(courseInfoForm);

        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        }else{
            return R.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("courseInfo/{id}")
    public R getById(@ApiParam(name = "id", value = "课程ID", required = true)
        @PathVariable String id){
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoForm);
    }


    @ApiOperation(value = "更新课程")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfoById(@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
        @RequestBody CourseInfoForm courseInfoForm){
        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(@ApiParam(name = "id", value = "课程ID", required = true)
        @PathVariable String id){
        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }
}

