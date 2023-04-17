package com.ytonline.examservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
public interface ExamPaperQuestionService extends IService<ExamPaperQuestion> {

    int saveId(ExamPaperQuestion examPaperQuestion);

    void pageQuery(Page<ExamPaperQuestion> pageInfo, ExamPaperQuestion examPaperQuestio,String paperId);

}
