package com.ytonline.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ytonline.commonutils.R;
import com.ytonline.cmsservice.entity.CrmBanner;
import com.ytonline.cmsservice.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/cmsservice/banneradmin")

public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageInfo = new Page<>(current, limit);
        crmBannerService.page(pageInfo,null);
        return R.success().data("current", pageInfo.getRecords()).data("total", pageInfo.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.success().data("banner", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner banner) {
        crmBannerService.save(banner);
        return R.success();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.success();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.success();
    }
}

