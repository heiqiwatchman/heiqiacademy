package com.heiqiwatchman.codeGenerator.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongfengw
 * @create 2022-09-26 20:15
 * @Description:
 * @Version 1.0
 */
public class TestEasyExcel {

    public static void main(String[] args) {
        //写操作
       // String filename = "D:\\write.xlsx";
        //调用easyexcel里面的方法实现写操作
        //write 方法：第一个参数是文件路径 第二个参数是实体类名称
       // EasyExcel.write(filename,EasyExcelDemoData.class).sheet("学生列表").doWrite(TestEasyExcel.data());

        //读操作
        String filename = "D:\\write.xlsx";
        EasyExcel.read(filename,EasyExcelDemoData.class,new EasyExcelListenter()).sheet().doRead();
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<EasyExcelDemoData> data() {
        List<EasyExcelDemoData> list = new ArrayList<EasyExcelDemoData>();
        for (int i = 0; i < 10; i++) {
            EasyExcelDemoData data = new EasyExcelDemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
