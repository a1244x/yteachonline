package com.ytonline.vod.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.ytonline.vod.utils.AliyunVodSDKUtils;
import com.ytonline.vod.utils.ConstantPropertiesUtil;
import com.ytonline.servicebase.handler.ytonlineException;
import com.ytonline.vod.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                if(StringUtils.isEmpty(videoId)){
                    throw new ytonlineException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new ytonlineException(20001, "vod 服务上传失败");
        }
    }

    @Override
    public void removeVideo(String id) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(id);

            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ytonlineException(20001, "视频删除失败");
        }
    }

    @Override
    public void removeVideoList(List videoIdList) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            String str = StringUtils.join(videoIdList.toArray(), ",");

            request.setVideoIds(str);

            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ytonlineException(20001, "视频删除失败");
        }
    }
}
