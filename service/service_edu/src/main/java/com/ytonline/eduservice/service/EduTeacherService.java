package com.ytonline.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    List<EduTeacher> teacherList();

    Map<String, Object> getAllTeacherFrontList(Page<EduTeacher> pageInfo);
}
