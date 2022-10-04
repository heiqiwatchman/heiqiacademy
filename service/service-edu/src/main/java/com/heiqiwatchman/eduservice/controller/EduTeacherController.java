package com.heiqiwatchman.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heiqiwatchman.commonutils.R;
import com.heiqiwatchman.eduservice.entity.EduTeacher;
import com.heiqiwatchman.eduservice.entity.vo.TeacherVO;
import com.heiqiwatchman.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-24
 */

@Api(description = "讲师管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询讲师信息")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit) {

        //1、创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>();
        IPage<EduTeacher> page = eduTeacherService.page(eduTeacherPage, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        //return R.ok().data("total",total).data("rows",records);
        return R.ok().data(map);
    }

    @ApiOperation(value = "分页条件查询讲师信息")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
                                  @RequestBody(required = false) TeacherVO teacherVO) {

        //1、创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper();
        String name = teacherVO.getName();
        Integer level = teacherVO.getLevel();
        String begin = teacherVO.getBegin();
        String end = teacherVO.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        //  排序
        queryWrapper.orderByDesc("gmt_create");
        IPage<EduTeacher> page = eduTeacherService.page(eduTeacherPage, queryWrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();


        return R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "添加讲师信息")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody(required = true) EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据讲师id查询")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师Id", required = true) @PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    @ApiOperation(value = "根据讲师对象修改讲师信息")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody(required = true) EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

