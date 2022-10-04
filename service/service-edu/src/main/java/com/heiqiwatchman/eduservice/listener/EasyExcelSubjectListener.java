package com.heiqiwatchman.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heiqiwatchman.eduservice.entity.EduSubject;
import com.heiqiwatchman.eduservice.entity.easyexcel.EasyExcelSubjectData;
import com.heiqiwatchman.eduservice.service.EduSubjectService;
import com.heiqiwatchman.servicebase.exception.HeiqiException;

import java.util.Map;

/**
 * @author hongfengw
 * @create 2022-09-26 20:51
 * @Description:
 * @Version 1.0
 */
public class EasyExcelSubjectListener extends AnalysisEventListener<EasyExcelSubjectData> {


    public EduSubjectService eduSubjectService;

    public EasyExcelSubjectListener() {
    }

    public EasyExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(EasyExcelSubjectData easyExcelSubjectData, AnalysisContext analysisContext) {
        if (easyExcelSubjectData == null) {
            throw new HeiqiException(20001, "文件数据为空");
        }
        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, easyExcelSubjectData.getOneSubjectName());
        if (existOneSubject == null) {//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(easyExcelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();


        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, easyExcelSubjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(easyExcelSubjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);

        wrapper.eq("parent_id", pid);
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
    //判断一级分类是否重复

    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
}
