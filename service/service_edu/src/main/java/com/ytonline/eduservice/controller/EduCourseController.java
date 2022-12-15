package com.ytonline.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.vo.CourseInfoVo;
import com.ytonline.eduservice.service.EduCourseService;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduCourse;
import com.ytonline.eduservice.entity.vo.CoursePublishVo;
import com.ytonline.eduservice.entity.vo.CourseQuery;
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
@RequestMapping("/eduservice/course")

public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //课程列表
    @GetMapping("coursePage/{current}/{limit}")
    public R coursePage(@PathVariable Long current,@PathVariable Long limit,@RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageInfo = new Page<>(current,limit);
        eduCourseService.pageQuery(pageInfo,courseQuery);
        List<EduCourse> records = pageInfo.getRecords();
        long total = pageInfo.getTotal();
        return R.success().data("total",total).data("rows",records);
    }

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String id = eduCourseService.saveCourseInfo(courseInfoVo);

        return R.success().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.success().data("courseInfo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.success();
    }

    //查课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo courseInfoForm = eduCourseService.publishCourseInfo(id);
        return R.success().data("publishCourse",courseInfoForm);
    }

    //课程发布
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.success();
    }

    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id) {
        eduCourseService.removeCourse(id);
        return R.success();
    }

}

