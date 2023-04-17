package com.ytonline.examservice.mapper;

import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamPaperMapper extends BaseMapper<ExamPaper> {
    ExamPaper getByExamId(String examId);

}
