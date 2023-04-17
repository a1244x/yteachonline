package com.ytonline.examservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.ytonline.examservice.mapper.ExamExamPaperMapper;
import com.ytonline.examservice.service.ExamExamPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-16
 */
@Service
public class ExamExamPaperServiceImpl extends ServiceImpl<ExamExamPaperMapper, ExamExamPaper> implements ExamExamPaperService {

    @Override
    public int saveId(ExamExamPaper examExamPaper) {
        ExamExamPaper checkId = baseMapper.getByExamId(examExamPaper.getExamId());
        if(checkId==null){
            baseMapper.insert(examExamPaper);
            return 1;
        }
        return 0;
    }

    @Override
    public void pageQuery(Page<ExamExamPaper> pageInfo, ExamExamPaper examExamPaper, String examId) {
        LambdaQueryWrapper<ExamExamPaper> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ExamExamPaper::getExamId,examId);
        lqw.orderByDesc(ExamExamPaper::getGmtCreate);

        if(examId == null){
            return;
        }

        baseMapper.selectPage(pageInfo,lqw);
    }



}
