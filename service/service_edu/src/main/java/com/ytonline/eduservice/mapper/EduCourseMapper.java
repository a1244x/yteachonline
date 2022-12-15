package com.ytonline.eduservice.mapper;

import com.ytonline.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytonline.eduservice.entity.vo.CoursePublishVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontInfoVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseFrontInfoVo getBaseCourseInfo(String courseId);
}
