package com.ytonline.eduservice.entity.vo;

import com.ytonline.eduservice.entity.EduCourse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseInfoVo extends EduCourse{

    @ApiModelProperty(value = "课程简介")
    private String description;
}
