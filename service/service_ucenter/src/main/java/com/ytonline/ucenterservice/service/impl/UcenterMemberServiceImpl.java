package com.ytonline.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytonline.commonutils.JwtUtils;
import com.ytonline.commonutils.MD5;
import com.ytonline.servicebase.handler.ytonlineException;
import com.ytonline.ucenterservice.entity.UcenterMember;
import com.ytonline.ucenterservice.entity.vo.InfoVo;
import com.ytonline.ucenterservice.entity.vo.LoginVo;
import com.ytonline.ucenterservice.entity.vo.RegisterVo;
import com.ytonline.ucenterservice.mapper.UcenterMemberMapper;
import com.ytonline.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-14
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();

        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new ytonlineException(20001,"邮箱或密码不能为空");
        }

        LambdaQueryWrapper<UcenterMember> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UcenterMember::getEmail,email);
        UcenterMember ucenterMember = baseMapper.selectOne(lqw);
        if(ucenterMember == null){
            throw new ytonlineException(20001,"查询不到此用户");
        }


        if(!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new ytonlineException(20001,"密码错误");
        }

        if(ucenterMember.getIsDisabled()){
            throw new ytonlineException(20001,"用户已被禁用");
        }

        String token = JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String email = registerVo.getEmail();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new ytonlineException(20001,"不能为空");
        }

        String emailCode = redisTemplate.opsForValue().get(email);
        if(!code.equals(emailCode)){
            throw new ytonlineException(20001,"验证码不正确");
        }

        LambdaQueryWrapper<UcenterMember> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UcenterMember::getEmail,email);
        Integer count = baseMapper.selectCount(lqw);
        if(count.intValue() > 0){
            throw new ytonlineException(20001,"此邮箱已被注册");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setEmail(email);
        ucenterMember.setPassword(com.ytonline.commonutils.MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("https://teachonline-file.oss-cn-guangzhou.aliyuncs.com/default.jpg");
        this.save(ucenterMember);
    }

}
