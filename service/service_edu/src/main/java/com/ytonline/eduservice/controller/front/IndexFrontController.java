package com.ytonline.eduservice.controller.front;

import com.ytonline.eduservice.entity.EduTeacher;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduCourse;
import com.ytonline.eduservice.service.EduCourseService;
import com.ytonline.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexFront")

public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public R index(){
        //前八条热门课程
        List<EduCourse> courseList = eduCourseService.courseList();

        //前4条名师
        List<EduTeacher> teacherList = eduTeacherService.teacherList();

        return R.success().data("courseList",courseList).data("teacherList",teacherList);
    }
}
