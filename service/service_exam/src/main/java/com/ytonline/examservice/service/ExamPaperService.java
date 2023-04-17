package com.ytonline.examservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.examservice.entity.vo.ExamPaperVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamPaperService extends IService<ExamPaper> {

    void pageQuery(Page<ExamPaper> pageInfo, ExamPaperVo examPaperVo);


    ExamPaper getByPaperInExamId(String id);
}
