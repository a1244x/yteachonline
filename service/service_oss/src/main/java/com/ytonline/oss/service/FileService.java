package com.ytonline.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadAvatar(MultipartFile file);
}
