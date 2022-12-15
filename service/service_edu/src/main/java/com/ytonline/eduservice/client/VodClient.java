package com.ytonline.eduservice.client;

import com.ytonline.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @DeleteMapping("/admin/vod/video/removeVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/admin/vod/video/deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
