package com.anhtester.PageObjectModel.testcases;

import com.anhtester.ExcelHelper.ExcelHelper;
import com.anhtester.PageObjectModel.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.keywords.WebUI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess(){
        loginPage = new LoginPage(driver);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","LoginPageData");
        for (int i =1; i<=2; i++){
            loginPage.loginCRM(
                    excelHelper.getCellData("EMAIL", i),
                    excelHelper.getCellData("PASSWORD", i)
            );
            WebUI.sleep(2);
            loginPage.verifyLoginSuccess();
            loginPage.logout();
        }
    }

    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage(driver);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","LoginPageData");
        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 3)
        );
        loginPage.verifyLoginFailed("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage(driver);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","LoginPageData");
        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL",3),
                excelHelper.getCellData("PASSWORD", 3)
        );
        loginPage.verifyLoginFailed("Invalid email or password");
    }
    @Test
    public void testLoginFailEmailNull(){
        loginPage = new LoginPage(driver);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","LoginPageData");
        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL",4),
                excelHelper.getCellData("PASSWORD", 4)
        );
        loginPage.verifyLoginFailed("The Email Address field is required.");
    }
    @Test
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage(driver);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","LoginPageData");
        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL",5),
                excelHelper.getCellData("PASSWORD", 5)
        );
        loginPage.verifyLoginFailed("The Password field is required.");
    }

}
