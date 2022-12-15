package com.ytonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.EduTeacher;
import com.ytonline.eduservice.mapper.EduTeacherMapper;
import com.ytonline.eduservice.service.EduTeacherService;
import com.ytonline.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-07
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<EduTeacher> teacherList() {
        LambdaQueryWrapper<EduTeacher> lqwTeacher = new LambdaQueryWrapper<>();
        lqwTeacher.orderByDesc(EduTeacher::getId);
        lqwTeacher.last("limit 4");
        List<EduTeacher> teacherList = baseMapper.selectList(lqwTeacher);
        return teacherList;
    }

    @Override
    public Map<String, Object> getAllTeacherFrontList(Page<EduTeacher> pageInfo) {
        LambdaQueryWrapper<EduTeacher> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(EduTeacher::getId);
        this.page(pageInfo, lqw);
        List<EduTeacher> records = pageInfo.getRecords();
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
}
