package com.ytonline.examservice.service.impl;

import com.ytonline.examservice.entity.ExamQuestionAnswer;
import com.ytonline.examservice.mapper.ExamQuestionAnswerMapper;
import com.ytonline.examservice.service.ExamQuestionAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-25
 */
@Service
public class ExamQuestionAnswerServiceImpl extends ServiceImpl<ExamQuestionAnswerMapper, ExamQuestionAnswer> implements ExamQuestionAnswerService {

    @Override
    public int checkExamIdAndUserId(String examId, String userId) {
        int i = baseMapper.checkExamIdAndUserId(examId, userId);
        if(i == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getFinalScore(String examId, String userId) {
        Integer finalScore = baseMapper.getFinalScore(examId, userId);
        if(finalScore == null){
            return 0;
        }
        return finalScore;
    }
}
