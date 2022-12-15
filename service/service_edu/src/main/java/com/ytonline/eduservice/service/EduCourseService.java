package com.ytonline.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.eduservice.entity.vo.CourseInfoVo;
import com.ytonline.eduservice.entity.vo.CoursePublishVo;
import com.ytonline.eduservice.entity.vo.CourseQuery;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontInfoVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void pageQuery(Page<EduCourse> pageInfo, CourseQuery courseQuery);

    void removeCourse(String id);

    List<EduCourse> courseList();

    List<EduCourse> getByTeacherId(String teacherId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageInfo, CourseFrontVo courseFrontVo);

    CourseFrontInfoVo getBaseCourseInfo(String courseId);

    void updatePageViewCount(String courseId);
}
