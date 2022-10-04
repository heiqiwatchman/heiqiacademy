package com.heiqiwatchman.eduservice.controller;


import com.alibaba.excel.EasyExcel;
import com.heiqiwatchman.commonutils.R;
import com.heiqiwatchman.eduservice.entity.easyexcel.EasyExcelSubjectData;
import com.heiqiwatchman.eduservice.entity.vo.SubjectNestedVO;
import com.heiqiwatchman.eduservice.listener.EasyExcelSubjectListener;
import com.heiqiwatchman.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author hongfengw
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {

        //上传excel文件
        eduSubjectService.saveSubject(file,eduSubjectService);

        return R.ok();
    }

    @ApiOperation(value = "课程分类树数据列表")
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        List<SubjectNestedVO> subjectNestedVoList = eduSubjectService.nestedList();
        return R.ok().data("list", subjectNestedVoList);
    }
}

