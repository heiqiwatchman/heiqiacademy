package com.heiqiwatchman.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heiqiwatchman.eduservice.entity.EduSubject;
import com.heiqiwatchman.eduservice.entity.easyexcel.EasyExcelSubjectData;
import com.heiqiwatchman.eduservice.entity.vo.SubjectNestedVO;
import com.heiqiwatchman.eduservice.entity.vo.SubjectVO;
import com.heiqiwatchman.eduservice.listener.EasyExcelSubjectListener;
import com.heiqiwatchman.eduservice.mapper.EduSubjectMapper;
import com.heiqiwatchman.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        //文件输入流
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, EasyExcelSubjectData.class,new EasyExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectNestedVO> nestedList() {

        //最终要的到的数据列表
        ArrayList<SubjectNestedVO> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类vo数据
        int count = subjects.size();

        for (int i = 0; i < count; i++) {

            EduSubject subject = subjects.get(i);

            //创建一级类别vo对象
            SubjectNestedVO subjectNestedVO = new SubjectNestedVO();

            BeanUtils.copyProperties(subject, subjectNestedVO);
            subjectNestedVoArrayList.add(subjectNestedVO);

            //填充二级分类vo数据
            ArrayList<SubjectVO> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){

                    //创建二级类别vo对象
                    SubjectVO subjectVO = new SubjectVO();
                    BeanUtils.copyProperties(subSubject, subjectVO);

                    subjectVoArrayList.add(subjectVO);
                }
            }
            subjectNestedVO.setChildren(subjectVoArrayList);

        }

        return subjectNestedVoArrayList;
    }
}
