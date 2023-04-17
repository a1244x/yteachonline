package com.ytonline.examservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.ExamExamPaper;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.ytonline.examservice.service.ExamExamPaperService;
import com.ytonline.examservice.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/examservice/examExamPaper")
public class ExamExamPaperController {

    @Autowired
    private ExamExamPaperService examExamPaperService;

    @Autowired
    private ExamPaperService examPaperService;


    @PostMapping("addExamAndPaper")
    public R addExamAndPaper(@RequestBody ExamExamPaper examExamPaper){

        int i = examExamPaperService.saveId(examExamPaper);
        if(i == 1){
            return R.success().message("添加成功");
        }else{
            return R.error().message("一个考试只能添加一份试卷！");
        }

    }

    @PostMapping("getExamAndPaper/{current1}/{limit1}")
    public R getExamAndPaper(@PathVariable Long current1, @PathVariable Long limit1,@RequestBody ExamExamPaper examExamPaper){
        Page<ExamExamPaper> pageInfo = new Page<>(current1,limit1);
        String examId = examExamPaper.getExamId();
        System.out.println(examId);
        examExamPaperService.pageQuery(pageInfo,examExamPaper,examId);

        List<ExamExamPaper> records = pageInfo.getRecords();

        long total1 = pageInfo.getTotal();

        return R.success().data("total1",total1).data("rows",records);


    }

    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        examExamPaperService.removeById(id);
        return R.success().message("删除成功");
    }

    @PostMapping("getByExamId/{id}")
    public R getByExamId(@PathVariable String id){
        ExamPaper examPaper = examPaperService.getByPaperInExamId(id);
        return R.success().data("examPaper",examPaper);
    }

}

