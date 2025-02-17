package com.anhtester.Prac27_PropertiesConfig.testcases;

import com.anhtester.ExcelHelper.PropertiesHelper;
import com.anhtester.Prac27_PropertiesConfig.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void testLoginSuccess(){
        loginPage = new LoginPage(driver);
        loginPage.loginCRM(ConfigData.EMAIL,ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();
    }
    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("1@example.com","123456");
        loginPage.verifyLoginFailed("Invalid email or password");
    }
    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com","000");
        loginPage.verifyLoginFailed("Invalid email or password");
    }
    @Test
    public void testLoginFailEmailNull(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("","123456");
        loginPage.verifyLoginFailed("The Email Address field is required.");
    }
    @Test
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com","");
        loginPage.verifyLoginFailed("The Password field is required.");
    }

}
