package com.ytonline.oss.service.impl;

import com.aliyun.oss.*;
import com.ytonline.oss.utils.ConstantPropertiesUtil;
import com.ytonline.oss.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadAvatar(MultipartFile file) {

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uploadUrl = null;

        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();

            //在文件名称前添加UUID
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid+filename;

            //把文件按日期分类
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            filename = dataPath +"/"+  filename;

            ossClient.putObject(bucketName, filename, inputStream);

            uploadUrl = "https://"+bucketName+"."+endPoint+"/"+filename;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return uploadUrl;
    }
}
