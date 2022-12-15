package com.ytonline.ucenterservice.service;

import com.ytonline.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytonline.ucenterservice.entity.vo.LoginVo;
import com.ytonline.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-14
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

}
