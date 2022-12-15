package com.ytonline.cmsservice.controller;


import com.ytonline.commonutils.R;
import com.ytonline.cmsservice.entity.CrmBanner;
import com.ytonline.cmsservice.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/cmsservice/bannerfront")

public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return R.success().data("bannerList", list);
    }

}

