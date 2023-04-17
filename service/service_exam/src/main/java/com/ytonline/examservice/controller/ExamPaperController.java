package com.ytonline.examservice.controller;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.client.naming.utils.RandomUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.*;
import com.ytonline.examservice.entity.dto.PaperDTO;
import com.ytonline.examservice.entity.vo.ExamPaperVo;
import com.ytonline.examservice.service.ExamPaperQuestionService;
import com.ytonline.examservice.service.ExamPaperService;
import com.ytonline.examservice.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/examservice/paper")
public class ExamPaperController {
    
    @Autowired
    private ExamPaperService examPaperService;


    @GetMapping("list")
    public R list(){
        List<ExamPaper> list = examPaperService.list(null);
        return R.success().data("Papers",list);
    }

    @PostMapping("pagePaper/{current}/{limit}")
    public R pageListPaper(@PathVariable Long current, @PathVariable Long limit, @RequestBody ExamPaperVo examPaperVo){

        Page<ExamPaper> pageInfo = new Page<>(current,limit);

        examPaperService.pageQuery(pageInfo,examPaperVo);

        List<ExamPaper> records = pageInfo.getRecords();

        long total = pageInfo.getTotal();

        return R.success().data("total",total).data("rows",records);
    }

    @PostMapping("addPaper")
    public R addPaper(@RequestBody ExamPaper examPaper){
        examPaperService.save(examPaper);
        return R.success().message("添加成功");
    }

    @GetMapping("getPaper/{id}")
    public R getPaper(@PathVariable String id){
        ExamPaper examPaper = examPaperService.getById(id);
        return R.success().data("examPaper",examPaper);
    }

    @PostMapping("updatePaper")
    public R updatePaper(@RequestBody ExamPaper examPaper){
        examPaperService.updateById(examPaper);
        return R.success().message("修改成功");
    }

    @DeleteMapping("deletePaper/{id}")
    public R deletePaper(@PathVariable String id){
        examPaperService.removeById(id);
        return R.success().message("删除成功");
    }




}

