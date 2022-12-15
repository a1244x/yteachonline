package com.ytonline.eduservice.service;

import com.ytonline.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.eduservice.entity.subjectVo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
