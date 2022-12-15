package com.ytonline.ucenterservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InfoVo {

    @ApiModelProperty(value = "邮箱")
    private String nickname;
}