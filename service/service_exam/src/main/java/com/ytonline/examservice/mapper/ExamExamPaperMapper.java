package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamExamPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-16
 */
public interface ExamExamPaperMapper extends BaseMapper<ExamExamPaper> {


    ExamExamPaper getByExamId(String examId);
}
