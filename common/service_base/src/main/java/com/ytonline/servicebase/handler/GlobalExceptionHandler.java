package com.ytonline.servicebase.handler;

import com.ytonline.commonutils.ExceptionUtil;
import com.ytonline.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message("执行了自定义异常");
    }

    @ExceptionHandler(ytonlineException.class)
    @ResponseBody
    public R error(ytonlineException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
    
}
