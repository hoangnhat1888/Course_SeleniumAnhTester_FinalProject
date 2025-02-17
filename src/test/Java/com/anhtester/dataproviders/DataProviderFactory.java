package com.anhtester.dataproviders;

import com.anhtester.ExcelHelper.ExcelHelper;
import com.anhtester.ExcelHelper.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {
    @DataProvider(name = "data_provider_01", parallel = true)
    public Object[][] dataProvider1() {
        return new Object[][]{{"First-Value"}, {"Second-Value"}, {"Third-Value"}};
    }

    @DataProvider(name = "data_provider_02")
    public Object[][] dataProvider2() {
        return new Object[][]{
                {"Value 1", "Value 2", "Value 3"},
                {"Value 4", "Value 5", "Value 6"},
                {"Value 7", "Value 8", "Value 9"}};
    }
    @DataProvider(name = "data_provider_03", parallel = true)
    public Object[][] dataProvider3() {
        return new Object[][]{
                {"Maison Margiela", "50", "France", 123123},
                {"Gucci", "10", "UK", 456456},
                {"Dior", "20", "US", 999999}
        };
    }

    @DataProvider(name = "data_provider_login", parallel = false)
    public Object[][] dataProviderLogin() {
        return new Object[][]{
                {"admin@example.com","123456"},
                {"customer@example.com","123456"}
        };
    }

    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginHRMFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        // Put link and Sheet Name into
        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "datatest/Login.xlsx", "Sheet1");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

}
