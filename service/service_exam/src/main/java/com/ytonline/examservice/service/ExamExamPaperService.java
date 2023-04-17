package com.ytonline.examservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.examservice.entity.ExamPaper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-16
 */
public interface ExamExamPaperService extends IService<ExamExamPaper> {

    int saveId(ExamExamPaper examExamPaper);

    void pageQuery(Page<ExamExamPaper> pageInfo, ExamExamPaper examExamPaper, String examId);

}
