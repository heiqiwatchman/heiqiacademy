package com.heiqiwatchman.eduservice.controller;

import com.heiqiwatchman.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hongfengw
 * @create 2022-09-25 18:09
 * @Description:
 * @Version 1.0
 */
@Api(description = "登录管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("/login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
