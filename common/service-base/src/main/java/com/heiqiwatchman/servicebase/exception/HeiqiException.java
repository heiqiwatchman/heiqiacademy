package com.heiqiwatchman.servicebase.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongfengw
 * @create 2022-09-24 12:36
 * @Description:自定义异常类
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeiqiException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

    @Override
    public String toString() {
        return "HeiqiException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
    }
}
