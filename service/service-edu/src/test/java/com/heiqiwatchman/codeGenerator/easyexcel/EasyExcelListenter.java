package com.heiqiwatchman.codeGenerator.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hongfengw
 * @create 2022-09-26 20:26
 * @Description:
 * @Version 1.0
 */
public class EasyExcelListenter  extends AnalysisEventListener<EasyExcelDemoData> {

    //创建list集合封装最终的数据
    List<EasyExcelDemoData> list = new ArrayList<EasyExcelDemoData>();

    //一行一行读取excel内容
    @Override
    public void invoke(EasyExcelDemoData easyExcelDemoData, AnalysisContext analysisContext) {
        System.out.println("****"+ easyExcelDemoData);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
