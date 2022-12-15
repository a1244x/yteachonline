package com.ytonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytonline.eduservice.entity.EduChapter;
import com.ytonline.eduservice.entity.vo.ChapterVo;
import com.ytonline.eduservice.entity.vo.VideoVo;
import com.ytonline.eduservice.mapper.EduChapterMapper;
import com.ytonline.eduservice.service.EduChapterService;
import com.ytonline.eduservice.service.EduVideoService;
import com.ytonline.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytonline.servicebase.handler.ytonlineException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourse(String courseId) {

        //根据课程id查所有的章节
        LambdaQueryWrapper<EduChapter> lqwChapter = new LambdaQueryWrapper<>();
        lqwChapter.eq(EduChapter::getCourseId,courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(lqwChapter);
        //根据课程id查所有的小节
        LambdaQueryWrapper<EduVideo> lqwVideo = new LambdaQueryWrapper<>();
        lqwVideo.eq(EduVideo::getCourseId,courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(lqwVideo);

        List<ChapterVo> finalList = new ArrayList<>();
        //封装章节
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            //封装小节
            List<VideoVo> videoList = new ArrayList<>();
            for (int t = 0; t < eduVideoList.size(); t++) {
                EduVideo eduVideo = eduVideoList.get(t);
                if(eduChapter.getId().equals(eduVideo.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    @Override
    public void deleteChapter(String chapterId) {
        LambdaQueryWrapper<EduVideo> lqwVideo = new LambdaQueryWrapper<>();
        lqwVideo.eq(EduVideo::getChapterId,chapterId);
        int count = eduVideoService.count(lqwVideo);
        if(count > 0){
            throw new ytonlineException(20001,"由于章节下有小节，不能进行删除");
        }else{
            this.removeById(chapterId);
        }
    }

    @Override
    public void removeChapterByCourseId(String id) {
        LambdaQueryWrapper<EduChapter> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduChapter::getCourseId,id);
        this.remove(lqw);
    }
}
