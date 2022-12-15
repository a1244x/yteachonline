package com.ytonline.ucenterservice.controller;


import com.ytonline.commonutils.EmailUtils;
import com.ytonline.commonutils.JwtUtils;
import com.ytonline.commonutils.R;
import com.ytonline.commonutils.ValidateCodeUtils;
import com.ytonline.servicebase.handler.ytonlineException;
import com.ytonline.ucenterservice.entity.UcenterMember;
import com.ytonline.ucenterservice.entity.vo.InfoVo;
import com.ytonline.ucenterservice.entity.vo.LoginVo;
import com.ytonline.ucenterservice.entity.vo.RegisterVo;
import com.ytonline.ucenterservice.service.UcenterMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author com.ytonline
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/ucenterservice/ucenter")

public class UcenterMemberController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = ucenterMemberService.login(loginVo);
        return R.success().data("token",token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.success();
    }

    @PostMapping("sendEmail")
    public R sendEmail(@RequestBody String email) throws UnsupportedEncodingException {
        String userEmail = email;
        userEmail = URLDecoder.decode(userEmail,"utf-8");
        StringBuffer buffer = new StringBuffer(userEmail);
        userEmail = buffer.deleteCharAt(userEmail.length() - 1).toString();
        System.out.println(userEmail);
        if(StringUtils.isNotEmpty(userEmail)){
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            boolean isSend = EmailUtils.sendEmail("验证码", "您的验证码为: " + code + " ,请妥善保管", userEmail);
            Map<String,Object> param = new HashMap<>();
            param.put("code", code);
            System.out.println(code);
            if(isSend) {
                redisTemplate.opsForValue().set(userEmail, code,5, TimeUnit.MINUTES);
                return R.success();
            } else {
                return R.error().message("发送验证码失败");
            }
        }
        return R.error().message("发送异常");
    }

    //根据token获取登录信息
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember = ucenterMemberService.getById(userId);
        return R.success().data("userInfo",ucenterMember);
    }



}

