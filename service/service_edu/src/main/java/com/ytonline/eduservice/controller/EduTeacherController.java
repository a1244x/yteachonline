package com.ytonline.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.eduservice.entity.EduTeacher;
import com.ytonline.eduservice.service.EduTeacherService;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.vo.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-07
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")

public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.success().data("teachers", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id){
        eduTeacherService.removeById(id);
        return R.success().message("删除成功");
    }

    @ApiOperation(value = "讲师分页查询")
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current",value = "当前页码",required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery){
        //创page对象
        Page<EduTeacher> pageInfo = new Page<>(current,limit);

        eduTeacherService.pageQuery(pageInfo,teacherQuery);
        List<EduTeacher> records = pageInfo.getRecords();
        long total = pageInfo.getTotal();
        return R.success().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "teacher",value = "讲师对象",required = true)
                        @RequestBody EduTeacher eduTeacher){
        eduTeacherService.save(eduTeacher);
        return R.success().message("新增成功");
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(@ApiParam(name = "id", value = "讲师ID", required = true)
                     @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.success().data("eduTeacher",teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R update(@RequestBody EduTeacher eduTeacher){
        eduTeacherService.updateById(eduTeacher);
        return R.success().message("修改成功");
    }
}

