package com.ytonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytonline.eduservice.service.EduVideoService;
import com.ytonline.eduservice.client.VodClient;
import com.ytonline.eduservice.entity.EduVideo;
import com.ytonline.eduservice.mapper.EduVideoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String id) {
        //根据课程id查所有视频
        LambdaQueryWrapper<EduVideo> lqwVideo = new LambdaQueryWrapper<>();
        lqwVideo.eq(EduVideo::getCourseId, id);
        List<EduVideo> eduVideoList = baseMapper.selectList(lqwVideo);

        //获取所有视频的云端ID
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(StringUtils.isNotEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }

        if (videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }

        LambdaQueryWrapper<EduVideo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduVideo::getCourseId, id);

        this.remove(lqw);
    }

    @Override
    public void removeVodByVideo(String id) {
        EduVideo eduVideo = this.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
    }
}
