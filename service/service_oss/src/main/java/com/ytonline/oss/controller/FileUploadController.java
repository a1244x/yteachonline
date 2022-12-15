package com.ytonline.oss.controller;

import com.ytonline.commonutils.R;
import com.ytonline.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Api(tags="阿里云文件管理")
 //跨域
@RestController
@RequestMapping("/eduoss/fileoss")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String uploadUrl = fileService.uploadAvatar(file);
        //返回r对象
        return R.success().message("文件上传成功").data("url", uploadUrl);

    }
}
