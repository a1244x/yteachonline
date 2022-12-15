package com.ytonline.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytonline.eduservice.entity.EduSubject;
import com.ytonline.eduservice.entity.excel.ExcelSubjectData;
import com.ytonline.eduservice.service.EduSubjectService;
import com.ytonline.servicebase.handler.ytonlineException;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener() {}
    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(ExcelSubjectData esd, AnalysisContext analysisContext) {
        if(esd == null) {
            throw new ytonlineException(20001,"添加失败");
        }
        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService,esd.getOneSubjectName());
        if(existOneSubject == null) {//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(esd.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(subjectService,esd.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(esd.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

}
