package com.ytonline.examservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamPaperQuestion;
import com.ytonline.examservice.service.ExamPaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/examservice/examPaperQuestion")
public class ExamPaperQuestionController {

    @Autowired
    private ExamPaperQuestionService examPaperQuestionService;

    @PostMapping("addPaperIdAandQuestionId")
    public R addPaperIdAandQuestionId(@RequestBody ExamPaperQuestion examPaperQuestion){

        int i = examPaperQuestionService.saveId(examPaperQuestion);
        if(i == 0){
            return R.success().message("添加成功");
        }else{
            return R.error().message("该题目已添加！，重新选择题目");
        }

    }

    @PostMapping("selectPaperId/{current1}/{limit1}")
    public R selectPaperId(@PathVariable Long current1, @PathVariable Long limit1,@RequestBody ExamPaperQuestion examPaperQuestion){
        Page<ExamPaperQuestion> pageInfo = new Page<>(current1,limit1);
        String paperId = examPaperQuestion.getPaperId();
        System.out.println(paperId);
        examPaperQuestionService.pageQuery(pageInfo,examPaperQuestion,paperId);

        List<ExamPaperQuestion> records = pageInfo.getRecords();

        long total1 = pageInfo.getTotal();

        return R.success().data("total1",total1).data("rows",records);


    }

    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        examPaperQuestionService.removeById(id);
        return R.success().message("删除成功");
    }





}

