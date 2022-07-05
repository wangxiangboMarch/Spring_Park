package com.wly.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        // 一行行读取excel 内容
        System.out.println("****" + demoData);
    }

    // 读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.println("表头" + headMap);
    }
    // 全部完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
