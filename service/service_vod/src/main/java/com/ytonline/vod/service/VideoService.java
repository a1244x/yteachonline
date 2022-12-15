package com.ytonline.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String id);

    void removeVideoList(List videoIdList);
}
