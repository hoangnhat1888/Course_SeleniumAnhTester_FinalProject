package com.anhtester.Prac31_TestListener.testcases;

import com.anhtester.Prac31_TestListener.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test(dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccess(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test(dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessFromExcelDataProvider(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM(email, password);
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("1@example.com", "123456");
        loginPage.verifyLoginFailed("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid() {
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com", "000");
        loginPage.verifyLoginFailed("Invalid email or password");
    }

    @Test
    public void testLoginFailEmailNull() {
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFailed("The Email Address field is required.");
    }

    @Test
    public void testLoginFailWithPasswordNull() {
        loginPage = new LoginPage(driver);

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFailed("The Password field is required.");
    }

}
