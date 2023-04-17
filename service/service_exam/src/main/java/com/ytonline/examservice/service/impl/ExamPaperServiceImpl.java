package com.ytonline.examservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.vo.ExamPaperVo;
import com.ytonline.examservice.mapper.ExamPaperMapper;
import com.ytonline.examservice.service.ExamPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
@Service
public class ExamPaperServiceImpl extends ServiceImpl<ExamPaperMapper, ExamPaper> implements ExamPaperService {

    public void pageQuery(Page<ExamPaper> pageParam, ExamPaperVo examPaperVo) {
        LambdaQueryWrapper<ExamPaper> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(ExamPaper::getGmtCreate);

        if(examPaperVo == null){
            baseMapper.selectPage(pageParam,lqw);
            return;
        }

        String name = examPaperVo.getName();

        lqw.like(StringUtils.isNotEmpty(name),ExamPaper::getName,name);

        baseMapper.selectPage(pageParam,lqw);
    }

    @Override
    public ExamPaper getByPaperInExamId(String id) {
        return baseMapper.getByExamId(id);
    }


}
