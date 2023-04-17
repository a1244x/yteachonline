package com.ytonline.examservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.ExamQuestion;
import com.ytonline.examservice.entity.vo.ExamQuestionVo;
import com.ytonline.examservice.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/examservice/question")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    @GetMapping("list")
    public R list(){
        List<ExamQuestion> list = examQuestionService.list(null);
        return R.success().data("questions",list);
    }

    @PostMapping("pageQuestion/{current}/{limit}")
    public R pageListQuestion(@PathVariable Long current, @PathVariable Long limit, @RequestBody ExamQuestionVo examQuestionVo){

        Page<ExamQuestion> pageInfo = new Page<>(current,limit);

        examQuestionService.pageQuery(pageInfo,examQuestionVo);

        List<ExamQuestion> records = pageInfo.getRecords();

        long total = pageInfo.getTotal();

        return R.success().data("total",total).data("rows",records);
    }

    @PostMapping("addQuestion")
    public R addQuestion(@RequestBody ExamQuestion examQuestion){
        examQuestionService.save(examQuestion);
        return R.success().message("添加成功");
    }

    @GetMapping("getQuestion/{id}")
    public R getQuestion(@PathVariable String id){
        ExamQuestion examQuestion = examQuestionService.getById(id);
        return R.success().data("examQuestion",examQuestion);
    }

    @PostMapping("updateQuestion")
    public R updateQuestion(@RequestBody ExamQuestion examQuestion){
        examQuestionService.updateById(examQuestion);
        return R.success().message("修改成功");
    }

    @DeleteMapping("deleteQuestion/{id}")
    public R deleteQuestion(@PathVariable String id){
        examQuestionService.removeById(id);
        return R.success().message("删除成功");
    }

    @PostMapping("getPaperQuestion/{id}")
    public R getPaperQuestion(@PathVariable String id){
        List<ExamQuestion> question = examQuestionService.getPaperQuestion(id);
        question.sort(Comparator.comparingInt(ExamQuestion::getType));
        int total = question.size();
        return R.success().data("total",total).data("question",question);
    }

}

