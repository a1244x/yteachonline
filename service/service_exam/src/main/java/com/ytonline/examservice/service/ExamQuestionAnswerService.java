package com.ytonline.examservice.service;

import com.ytonline.examservice.entity.ExamQuestionAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-25
 */
public interface ExamQuestionAnswerService extends IService<ExamQuestionAnswer> {

    int checkExamIdAndUserId(String examId, String userId);

    int getFinalScore(String examId, String userId);
}
