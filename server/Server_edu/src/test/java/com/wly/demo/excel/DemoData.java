package com.wly.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    // excel的表头
    @ExcelProperty("学生编号")
    private Integer sno;

    @ExcelProperty("学生的姓名")
    private String sname;

}
