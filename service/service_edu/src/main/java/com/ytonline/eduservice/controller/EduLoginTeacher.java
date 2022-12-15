package com.ytonline.eduservice.controller;

import com.ytonline.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "讲师登录")
@RestController

@RequestMapping("/eduservice/user")
public class EduLoginTeacher {

    @PostMapping("login")
    public R login(){
        return R.success().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.success().data("roles","[admin]").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180521%2F74076df069744f4cbf6bed3d4e86c8a8.gif&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673074382&t=8350f0d981025eda4df382d520a513fc");
    }

}
