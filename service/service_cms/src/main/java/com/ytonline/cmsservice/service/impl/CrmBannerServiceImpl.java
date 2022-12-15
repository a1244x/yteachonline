package com.ytonline.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytonline.cmsservice.entity.CrmBanner;
import com.ytonline.cmsservice.mapper.CrmBannerMapper;
import com.ytonline.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-13
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner",key = "'selectIndexList'")
    public List<CrmBanner> selectAllBanner() {
        LambdaQueryWrapper<CrmBanner> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(CrmBanner::getId);
        lqw.last("limit 2");
        List<CrmBanner> bannerList = baseMapper.selectList(null);
        return bannerList;
    }
}
