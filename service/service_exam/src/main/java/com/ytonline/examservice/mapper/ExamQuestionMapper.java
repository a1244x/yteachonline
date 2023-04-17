package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-14
 */
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {

     List<ExamQuestion> getPaperQuestion(String paperId);

}
