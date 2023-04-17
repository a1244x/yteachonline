package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamQuestionAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-25
 */
public interface ExamQuestionAnswerMapper extends BaseMapper<ExamQuestionAnswer> {

    int checkExamIdAndUserId(String examId,String userId);

    Integer getFinalScore(String examId, String userId);

}
