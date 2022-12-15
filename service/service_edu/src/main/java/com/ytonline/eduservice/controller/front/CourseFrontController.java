package com.ytonline.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduCourse;
import com.ytonline.eduservice.entity.vo.ChapterVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontInfoVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontVo;
import com.ytonline.eduservice.service.EduChapterService;
import com.ytonline.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")

public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService EduChapterService;

    //条件+分页查询
    @PostMapping("getFrontCourseList/{current}/{limit}")
    public R getFrontCourseList(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){

        Page<EduCourse> pageInfo = new Page<>(current,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageInfo,courseFrontVo);

        return R.success().data(map);
    }

    //课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId) {

        CourseFrontInfoVo courseFrontInfoVo = eduCourseService.getBaseCourseInfo(courseId);

        List<ChapterVo> chapterVideoList = EduChapterService.getChapterVideoByCourse(courseId);

        eduCourseService.updatePageViewCount(courseId);
        return R.success().data("courseFrontInfoVo",courseFrontInfoVo).data("chapterVideoList",chapterVideoList);
    }
}
