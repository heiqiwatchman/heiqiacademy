package com.heiqiwatchman.eduservice.service.impl;

import com.heiqiwatchman.eduservice.entity.EduCourse;
import com.heiqiwatchman.eduservice.entity.EduCourseDescription;
import com.heiqiwatchman.eduservice.entity.vo.CourseInfoVO;
import com.heiqiwatchman.eduservice.mapper.EduCourseMapper;
import com.heiqiwatchman.eduservice.service.EduCourseDescriptionService;
import com.heiqiwatchman.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heiqiwatchman.servicebase.exception.HeiqiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Override
    public String addCourseInfo(CourseInfoVO courseInfoVO) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        course.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoVO, course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            throw new HeiqiException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();

        courseDescription.setDescription(courseInfoVO.getDescription());

        courseDescription.setId(course.getId());
        boolean resultDescription = eduCourseDescriptionService.save(courseDescription);
        if(!resultDescription){
            throw new HeiqiException(20001, "课程详情信息保存失败");
        }

        return course.getId();

    }
}
