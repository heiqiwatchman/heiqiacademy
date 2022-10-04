package com.heiqiwatchman.eduservice.service;

import com.heiqiwatchman.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heiqiwatchman.eduservice.entity.vo.CourseInfoVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoVO courseInfoVO);
}
