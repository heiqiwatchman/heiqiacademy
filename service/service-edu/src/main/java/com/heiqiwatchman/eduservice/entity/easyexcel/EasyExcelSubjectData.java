package com.heiqiwatchman.eduservice.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author hongfengw
 * @create 2022-09-26 20:45
 * @Description:
 * @Version 1.0
 */
@Data
public class EasyExcelSubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
