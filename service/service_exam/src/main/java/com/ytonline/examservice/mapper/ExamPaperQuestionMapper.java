package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamPaperQuestionMapper extends BaseMapper<ExamPaperQuestion> {
    ExamPaperQuestion checkId(String paperId,String questionId);

}
