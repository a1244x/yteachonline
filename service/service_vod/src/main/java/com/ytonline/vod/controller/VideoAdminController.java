package com.ytonline.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.ytonline.vod.service.VideoService;
import com.ytonline.commonutils.R;
import com.ytonline.vod.utils.AliyunVodSDKUtils;
import com.ytonline.vod.utils.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(tags="阿里云视频管理")
 //跨域
@RestController
@RequestMapping("/admin/vod/video")
public class VideoAdminController {
    @Autowired
    private VideoService videoService;

    @ApiParam(value = "文件上传")
    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = videoService.uploadVideo(file);
        return R.success().message("视频上传成功").data("videoId", videoId);
    }

    //删除单个视频
    @DeleteMapping("removeVideo/{id}")
    public R removeVideo(@PathVariable String id){
        videoService.removeVideo(id);
        return R.success();
    }

    //删除多个视频
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        videoService.removeVideoList(videoIdList);
        return R.success();
    }

    @GetMapping("getVideoPlay/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.success().message("获取凭证成功").data("playAuth", playAuth);
    }
}
