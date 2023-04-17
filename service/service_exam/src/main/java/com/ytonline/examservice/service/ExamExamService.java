package com.ytonline.examservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamQuestion;
import com.ytonline.examservice.entity.vo.ExamExamVo;
import com.ytonline.examservice.entity.vo.ExamQuestionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamExamService extends IService<ExamExam> {
    void pageQuery(Page<ExamExam> pageParam, ExamExamVo examExamVo);


}
