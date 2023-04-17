package com.ytonline.examservice.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.ytonline.commonutils.R;
import com.ytonline.examservice.entity.ExamQuestionAnswer;
import com.ytonline.examservice.service.ExamQuestionAnswerService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/examservice/examQuestionAnswer")
public class ExamQuestionAnswerController {

    @Autowired
    private ExamQuestionAnswerService examQuestionAnswerService;

    @PostMapping("addAnswer")
    public R addAnswer(@RequestBody JSONArray jsonArray) {

        List list = JSONArray.parseArray(jsonArray.toJSONString());

        System.out.println(list);

        examQuestionAnswerService.saveBatch(list);
        return R.success().message("1");
    }

    @GetMapping("checkExamIdAndUserId/{examId}/{userId}")
    public R checkExamIdAndUserId(@PathVariable String examId,@PathVariable String userId){
        int i =examQuestionAnswerService.checkExamIdAndUserId(examId,userId);
        if(i == 0){
            return R.success();
        }
        return R.error().message("进入失败！");
    }

    @GetMapping("getFinalScore/{examId}/{userId}")
    public R getFinalScore(@PathVariable String examId,@PathVariable String userId){
        Integer score = examQuestionAnswerService.getFinalScore(examId,userId);
        return R.success().data("score",score);
    }

}

