package com.ytonline.eduservice.service;

import com.ytonline.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-10
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourse(String courseId);

    void deleteChapter(String chapterId);

    void removeChapterByCourseId(String id);
}
