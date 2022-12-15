package com.ytonline.eduservice.controller;


import com.ytonline.eduservice.service.EduSubjectService;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.subjectVo.OneSubject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-08
 */
@RestController
@RequestMapping("/eduservice/subject")

public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.saveSubject(file,eduSubjectService);
        //判断返回集合是否为空
        return R.success();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.success().data("list",list);
    }
}

