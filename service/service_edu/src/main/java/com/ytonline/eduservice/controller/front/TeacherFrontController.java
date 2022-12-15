package com.ytonline.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduCourse;
import com.ytonline.eduservice.entity.EduTeacher;
import com.ytonline.eduservice.service.EduCourseService;
import com.ytonline.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")

public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("getTeacherFrontList/{current}/{limit}")
    public R getTeacherFrontList(@PathVariable Long current,@PathVariable Long limit) {

        Page<EduTeacher> pageInfo = new Page<>(current,limit);
        Map<String,Object> map = eduTeacherService.getAllTeacherFrontList(pageInfo);

        return R.success().data(map);
    }

    @GetMapping("getTeacherInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable String teacherId) {
        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);

        List<EduCourse> courseList = eduCourseService.getByTeacherId(teacherId);
        return R.success().data("teacher",eduTeacher).data("courseList",courseList);
    }
}
