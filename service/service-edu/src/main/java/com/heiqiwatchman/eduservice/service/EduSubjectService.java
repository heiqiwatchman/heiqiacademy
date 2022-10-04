package com.heiqiwatchman.eduservice.service;

import com.heiqiwatchman.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heiqiwatchman.eduservice.entity.vo.SubjectNestedVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectNestedVO> nestedList();

}
