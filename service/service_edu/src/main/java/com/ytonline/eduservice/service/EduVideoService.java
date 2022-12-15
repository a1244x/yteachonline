package com.ytonline.eduservice.service;

import com.ytonline.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);

    void removeVodByVideo(String id);
}
