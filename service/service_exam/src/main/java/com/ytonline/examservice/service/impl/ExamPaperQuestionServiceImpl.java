package com.ytonline.examservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.ytonline.examservice.mapper.ExamPaperQuestionMapper;
import com.ytonline.examservice.service.ExamPaperQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ExamPaperQuestionServiceImpl extends ServiceImpl<ExamPaperQuestionMapper, ExamPaperQuestion> implements ExamPaperQuestionService {

    @Override
    public int saveId(ExamPaperQuestion examPaperQuestion) {
        ExamPaperQuestion checkId = baseMapper.checkId(examPaperQuestion.getPaperId(), examPaperQuestion.getQuestionId());
        if(checkId!=null){
            return 1;
        }
        baseMapper.insert(examPaperQuestion);

        return 0;
    }

    @Override
    public void pageQuery(Page<ExamPaperQuestion> pageInfo, ExamPaperQuestion examPaperQuestio, String paperId) {
        LambdaQueryWrapper<ExamPaperQuestion> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ExamPaperQuestion::getPaperId,paperId);
        lqw.orderByDesc(ExamPaperQuestion::getGmtCreate);

        if(paperId == null){
            return;
        }

        baseMapper.selectPage(pageInfo,lqw);
    }

}
