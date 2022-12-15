package com.ytonline.eduservice.controller;


import com.ytonline.eduservice.entity.vo.ChapterVo;
import com.ytonline.eduservice.service.EduChapterService;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduChapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
@RestController
@RequestMapping("/eduservice/chapter")

public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //大纲列表,根据课程id查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourse(courseId);
        return R.success().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.success();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.success().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.success();
    }

    //删除
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        eduChapterService.deleteChapter(chapterId);
        return R.success();
    }

}

