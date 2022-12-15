package com.ytonline.cmsservice.service;

import com.ytonline.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-13
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
}
