package com.ytonline.examservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamQuestion;
import com.ytonline.examservice.entity.vo.ExamQuestionVo;
import com.ytonline.examservice.mapper.ExamQuestionMapper;
import com.ytonline.examservice.service.ExamQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-14
 */
@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {

    @Override
    public void pageQuery(Page<ExamQuestion> pageParam, ExamQuestionVo examQuestionVo) {
        LambdaQueryWrapper<ExamQuestion> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(ExamQuestion::getGmtCreate);

        if(examQuestionVo == null){
            baseMapper.selectPage(pageParam,lqw);
            return;
        }

        String name = examQuestionVo.getName();

        lqw.like(StringUtils.isNotEmpty(name),ExamQuestion::getName,name);

        baseMapper.selectPage(pageParam,lqw);
    }

    @Override
    public List<ExamQuestion> getPaperQuestion(String paperId) {
        List<ExamQuestion> paperQuestion = baseMapper.getPaperQuestion(paperId);
        return paperQuestion;
    }

}
