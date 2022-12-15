package com.ytonline.eduservice.controller;


import com.ytonline.eduservice.service.EduVideoService;
import com.ytonline.commonutils.R;
import com.ytonline.eduservice.entity.EduVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
@RestController
@RequestMapping("/eduservice/video")

public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.success();
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        //根据小节id获取视频id

        eduVideoService.removeVodByVideo(videoId);
        eduVideoService.removeById(videoId);
        return R.success();
    }

    @GetMapping("getVideoInfoById/{videoId}")
    public R getVideoInfoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.success().data("video",eduVideo);
    }

    @PostMapping("updateVideoInfo")
    public R updateVideoInfo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.success();
    }
}

