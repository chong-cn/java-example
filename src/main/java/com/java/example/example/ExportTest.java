package com.java.example.example;

import com.java.example.utils.ExportExcelUtil;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportTest {

    public static void main(String[] args) throws Exception {
        ExportExcelUtil<UserTagExcelModel> util = new ExportExcelUtil<UserTagExcelModel>();
        // 准备数据
        String[] tagKeyArr = {"aa-vip", "bb-vip", "c-vip"};
        int min = 0, max = 2;
        int index = randomNum(min, max);
        int uid = 201;
        List<UserTagExcelModel> list = new ArrayList<>();
        for (int i = 0; i < 4000; i++) {
            int temp = uid + i;
            list.add(new UserTagExcelModel("提币限额", "vip", new Integer(temp)));
        }
        String[] columnNames = {"客户标签分类", "客户标签代码", "客户UID"};
        String excelTitle = "导入标签用户表";
        String location = "/Users/Mac/Workspace/WBFex/DevDocument/测试与排期/UserTagExcelModel.xlsx";
        util.exportExcel(excelTitle, columnNames, list, new FileOutputStream(location), ExportExcelUtil.EXCEl_FILE_2007);
    }

    public static int randomNum(int min, int max) {
        int index = (int) (Math.random() * (max - min) + min);
        return index;
    }

}
