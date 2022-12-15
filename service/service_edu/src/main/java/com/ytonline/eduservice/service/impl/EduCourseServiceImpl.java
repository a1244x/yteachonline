package com.ytonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.EduCourseDescription;
import com.ytonline.eduservice.entity.EduTeacher;
import com.ytonline.eduservice.entity.vo.CourseInfoVo;
import com.ytonline.eduservice.entity.vo.CoursePublishVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontInfoVo;
import com.ytonline.eduservice.entity.vo.frontVo.CourseFrontVo;
import com.ytonline.eduservice.mapper.EduCourseMapper;
import com.ytonline.eduservice.service.EduChapterService;
import com.ytonline.eduservice.service.EduCourseDescriptionService;
import com.ytonline.eduservice.service.EduCourseService;
import com.ytonline.eduservice.service.EduVideoService;
import com.ytonline.eduservice.entity.EduCourse;
import com.ytonline.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        this.save(eduCourse);

        String cid = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //查询简介表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        //修改课程表
        this.updateById(eduCourse);

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //修改简介表
        eduCourseDescriptionService.updateById(eduCourseDescription);

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageInfo, CourseQuery courseQuery) {
        LambdaQueryWrapper<EduCourse> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(EduCourse::getGmtModified);

        if(courseQuery == null){
            baseMapper.selectPage(pageInfo,lqw);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        lqw.like(StringUtils.isNotEmpty(title),EduCourse::getTitle,title);
        lqw.like(StringUtils.isNotEmpty(teacherId),EduCourse::getTeacherId,teacherId);
        lqw.like(StringUtils.isNotEmpty(subjectParentId),EduCourse::getSubjectParentId,subjectParentId);
        lqw.like(StringUtils.isNotEmpty(subjectId),EduCourse::getSubjectId,subjectId);

        baseMapper.selectPage(pageInfo,lqw);
    }

    @Override
    public void removeCourse(String id) {
        //先删除小节
        eduVideoService.removeVideoByCourseId(id);
        //再删除章节
        eduChapterService.removeChapterByCourseId(id);
        //再删除描述
        eduCourseDescriptionService.removeById(id);
        //删除课程本身
        this.removeById(id);
    }

    @Override
    public List<EduCourse> courseList() {
        LambdaQueryWrapper<EduCourse> lqwCourse = new LambdaQueryWrapper<>();
        lqwCourse.orderByDesc(EduCourse::getId);
        lqwCourse.last("limit 8");
        List<EduCourse> courseList = baseMapper.selectList(lqwCourse);
        return courseList;
    }

    @Override
    public List<EduCourse> getByTeacherId(String teacherId) {

        LambdaQueryWrapper<EduCourse> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduCourse::getTeacherId,teacherId);
        lqw.orderByDesc(EduCourse::getGmtModified);

        List<EduCourse> courseList = this.list(lqw);

        return courseList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageInfo, CourseFrontVo courseFrontVo) {
        LambdaQueryWrapper<EduCourse> lqw = new LambdaQueryWrapper<>();

        String subjectParentId = courseFrontVo.getSubjectParentId();
        String subjectId = courseFrontVo.getSubjectId();
        String buyCountSort = courseFrontVo.getBuyCountSort();
        String gmtCreateSort = courseFrontVo.getGmtCreateSort();
        String priceSort = courseFrontVo.getPriceSort();

        lqw.like(StringUtils.isNotEmpty(subjectParentId),EduCourse::getSubjectParentId,subjectParentId);
        lqw.like(StringUtils.isNotEmpty(subjectId),EduCourse::getSubjectId,subjectId);
        lqw.like(StringUtils.isNotEmpty(buyCountSort),EduCourse::getBuyCount,buyCountSort);
        lqw.like(StringUtils.isNotEmpty(gmtCreateSort),EduCourse::getGmtCreate,gmtCreateSort);
        lqw.like(StringUtils.isNotEmpty(priceSort),EduCourse::getPrice,priceSort);

        this.page(pageInfo,lqw);
        List<EduCourse> records = pageInfo.getRecords();
        long current = pageInfo.getCurrent();
        long pages = pageInfo.getPages();
        long size = pageInfo.getSize();
        long total = pageInfo.getTotal();
        boolean hasNext = pageInfo.hasNext();
        boolean hasPrevious = pageInfo.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseFrontInfoVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Override
    public void updatePageViewCount(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        eduCourse.setViewCount(eduCourse.getViewCount()+1);
        baseMapper.updateById(eduCourse);
    }
}
