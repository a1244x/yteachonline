package com.ytonline.aclservice.service.impl;

import com.ytonline.aclservice.entity.UserRole;
import com.ytonline.aclservice.mapper.UserRoleMapper;
import com.ytonline.aclservice.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
