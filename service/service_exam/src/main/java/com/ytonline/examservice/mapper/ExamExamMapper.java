package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytonline.examservice.entity.ExamPaper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamExamMapper extends BaseMapper<ExamExam> {
    ExamExam getByExamId(String examId);

}
