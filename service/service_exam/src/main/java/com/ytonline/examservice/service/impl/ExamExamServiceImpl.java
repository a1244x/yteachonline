package com.ytonline.examservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExam;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamQuestion;
import com.ytonline.examservice.entity.vo.ExamExamVo;
import com.ytonline.examservice.mapper.ExamExamMapper;
import com.ytonline.examservice.service.ExamExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
@Service
public class ExamExamServiceImpl extends ServiceImpl<ExamExamMapper, ExamExam> implements ExamExamService {

    @Override
    public void pageQuery(Page<ExamExam> pageParam, ExamExamVo examExamVo) {
        LambdaQueryWrapper<ExamExam> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(ExamExam::getGmtCreate);

        if(examExamVo == null){
            baseMapper.selectPage(pageParam,lqw);
            return;
        }

        String name = examExamVo.getName();

        lqw.like(StringUtils.isNotEmpty(name),ExamExam::getName,name);

        baseMapper.selectPage(pageParam,lqw);
    }



}
