package com.heiqiwatchman.eduservice.controller;


import com.heiqiwatchman.commonutils.R;
import com.heiqiwatchman.eduservice.entity.vo.CourseInfoVO;
import com.heiqiwatchman.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
@Api(description = "课程管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(
            @ApiParam(name = "CourseInfoVO", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVO courseInfoVO) {
        String courseId = eduCourseService.addCourseInfo(courseInfoVO);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }
    }
}

