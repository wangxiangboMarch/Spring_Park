package com.wly.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class testEasyExcel {

    public static void main(String[] args) {
//        writeExcel();

        readExcel();
    }

    private static void readExcel() {
        String filename = "/Users/macmini/Desktop/test1.xlsx";

        EasyExcel.read(filename,DemoData.class, new ExcelListener()).sheet().doRead();
    }

    private static void writeExcel() {
        // excel 的写文件
        // 文件位置
        String filename = "/Users/macmini/Desktop/test1.xlsx";
        // 文件的路径名称 和 实体类的类型
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData() );
    }

    private static List<DemoData> getData() {

        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("001==" + i);
            list.add(data);
        }
        return list;
    }
}
