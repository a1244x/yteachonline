package com.ytonline.examservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.ExamExam;
import com.ytonline.examservice.entity.ExamPaper;
import com.ytonline.examservice.entity.ExamExam;
import com.ytonline.examservice.entity.vo.ExamExamVo;
import com.ytonline.examservice.entity.vo.ExamExamVo;
import com.ytonline.examservice.service.ExamExamService;
import com.ytonline.examservice.service.impl.ExamExamServiceImpl;
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
@RequestMapping("/examservice/exam")
public class ExamExamController {

    @Autowired
    private ExamExamService examExamService;

    @GetMapping("list")
    public R list(){
        LambdaQueryWrapper<ExamExam> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(ExamExam::getState).orderByAsc(ExamExam::getStartTime);
        List<ExamExam> list = examExamService.list(lqw);
        long total = list.size();
        return R.success().data("rows",list).data("total",total);
    }

    @PostMapping("pageExam/{current}/{limit}")
    public R pageListExam(@PathVariable Long current, @PathVariable Long limit, @RequestBody ExamExamVo examExamVo){

        Page<ExamExam> pageInfo = new Page<>(current,limit);

        examExamService.pageQuery(pageInfo,examExamVo);

        List<ExamExam> records = pageInfo.getRecords();

        long total = pageInfo.getTotal();

        return R.success().data("total",total).data("rows",records);
    }

    @PostMapping("addExam")
    public R addExam(@RequestBody ExamExam examExam){
        examExamService.save(examExam);
        return R.success().message("添加成功");
    }

    @GetMapping("getExam/{id}")
    public R getExam(@PathVariable String id){
        ExamExam examExam = examExamService.getById(id);
        return R.success().data("examExam",examExam);
    }

    @PostMapping("updateExam")
    public R updateExam(@RequestBody ExamExam examExam){
        examExamService.updateById(examExam);
        return R.success().message("修改成功");
    }

    @DeleteMapping("deleteExam/{id}")
    public R deleteExam(@PathVariable String id){
        examExamService.removeById(id);
        return R.success().message("删除成功");
    }

}

