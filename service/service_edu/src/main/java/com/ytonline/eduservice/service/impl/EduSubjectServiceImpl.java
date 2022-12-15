package com.ytonline.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytonline.eduservice.entity.EduSubject;
import com.ytonline.eduservice.entity.excel.ExcelSubjectData;
import com.ytonline.eduservice.mapper.EduSubjectMapper;
import com.ytonline.eduservice.service.EduSubjectService;
import com.ytonline.servicebase.handler.ytonlineException;
import com.ytonline.eduservice.entity.subjectVo.OneSubject;
import com.ytonline.eduservice.entity.subjectVo.TwoSubject;
import com.ytonline.eduservice.listener.SubjectExcelListener;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dd.ytonline
 * @since 2022-12-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new ytonlineException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查一级分类
        LambdaQueryWrapper<EduSubject> lqwOne = new LambdaQueryWrapper<>();
        lqwOne.eq(EduSubject::getParentId,"0");
        List<EduSubject> oneEduSubject = baseMapper.selectList(lqwOne);

        //查二级分类
        LambdaQueryWrapper<EduSubject> lqwTwo = new LambdaQueryWrapper<>();
        lqwTwo.ne(EduSubject::getParentId,"0");
        List<EduSubject> TwoEduSubject = baseMapper.selectList(lqwTwo);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneEduSubject.size(); i++) {
            EduSubject eduSubject1 = oneEduSubject.get(i);

            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject1,oneSubject);
            finalSubjectList.add(oneSubject);

            //封装二级分类
            List<TwoSubject> finalTwoSubjectList = new ArrayList<>();
            for (int t = 0; t < TwoEduSubject.size(); t++) {
                EduSubject eduSubject2 = TwoEduSubject.get(t);
                if(eduSubject1.getId().equals(eduSubject2.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    finalTwoSubjectList.add(twoSubject);
                }
                oneSubject.setChildren(finalTwoSubjectList);
            }
        }
        return finalSubjectList;
    }
}
