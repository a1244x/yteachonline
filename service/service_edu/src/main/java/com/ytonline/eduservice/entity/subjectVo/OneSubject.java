package com.ytonline.eduservice.entity.subjectVo;

import lombok.Data;

import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String title;

    private List<TwoSubject> children;
}
