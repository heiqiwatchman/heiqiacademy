package com.heiqiwatchman.servicebase.exception;

import com.heiqiwatchman.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hongfengw
 * @create 2022-09-24 12:25
 * @Description: 自定义异常处理工具类
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HeiqiException.class)
    @ResponseBody
    public R error(HeiqiException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
