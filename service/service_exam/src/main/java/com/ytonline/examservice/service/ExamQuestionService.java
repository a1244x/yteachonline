package com.ytonline.examservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.examservice.entity.vo.ExamQuestionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-14
 */
public interface ExamQuestionService extends IService<ExamQuestion> {
    void pageQuery(Page<ExamQuestion> pageParam, ExamQuestionVo teacherQuery);

    List<ExamQuestion> getPaperQuestion(String paperId);

}
