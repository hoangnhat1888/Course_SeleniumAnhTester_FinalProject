package com.anhtester.Prac30_ScreenShoot_RecordVideo;

import com.anhtester.ExcelHelper.CaptureHelper;
import com.anhtester.Prac27_PropertiesConfig.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DemoScreenShoot extends BaseTest {
    LoginPage loginPage;

    @Test
    public void testHomePage1() {
        loginPage = new LoginPage(driver);
//        WebUI.openURL("https://anhtester.com");
        Assert.assertEquals(DriverManager.getDriver().getTitle(),"Anh Tester Automation Testing");

        CaptureHelper.screenShoot("picture");
        System.out.println("1. Screenshot success!! ");

        WebUI.clickElement(By.xpath("//a[@id='btn-login']"));
        WebUI.waitForPageLoaded();

        CaptureHelper.screenShoot("picture");
        System.out.println("2. Screenshot success !!");
    }
}
