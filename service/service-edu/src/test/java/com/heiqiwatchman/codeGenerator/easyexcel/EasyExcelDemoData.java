package com.heiqiwatchman.codeGenerator.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author hongfengw
 * @create 2022-09-26 20:12
 * @Description:
 * @Version 1.0
 */
@Data
public class EasyExcelDemoData {

    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;
}
