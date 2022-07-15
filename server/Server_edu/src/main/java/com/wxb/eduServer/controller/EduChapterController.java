package com.wxb.eduServer.controller;


import com.wxb.eduServer.entity.EduChapter;
import com.wxb.eduServer.entity.chapter.ChapterVo;
import com.wxb.eduServer.service.EduChapterService;
import com.wxb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wangler
 * @since 2022-07-06
 */
@Api(description="课程章节管理")
@RestController
@RequestMapping("/eduServer/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nestedList/{courseId}")
    public R nestedListByCourseId(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                      @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }



    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(@ApiParam(name = "chapterVo", value = "章节对象", required = true)
        @RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }


    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public R getById(@ApiParam(name = "id", value = "章节ID", required = true)
        @PathVariable String id){
        EduChapter chapter = chapterService.getById(id);
        return R.ok().data("item", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id", value = "章节ID", required = true)
        @PathVariable String id, @ApiParam(name = "chapter", value = "章节对象", required = true)
        @RequestBody EduChapter chapter){
        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "章节ID", required = true)
        @PathVariable String id){
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

