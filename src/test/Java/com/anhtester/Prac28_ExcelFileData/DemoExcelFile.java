package com.anhtester.Prac28_ExcelFileData;

import com.anhtester.ExcelHelper.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcelFile {
    @Test(priority = 1)
    public void getDataFromExcel(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","Sheet1");

        System.out.println(excelHelper.getCellData("EMAIL",1));
        System.out.println(excelHelper.getCellData("PASSWORD",1));

        // Get mupltiple data
        for(int i=1; i<= 3; i++){
            System.out.println(excelHelper.getCellData("EMAIL",i));
            System.out.println(excelHelper.getCellData("PASSWORD",i));
        }

        // Set data
        excelHelper.setCellData("Passed","STATUS",1);
        excelHelper.setCellData("Failed","STATUS",2);
    }
}
