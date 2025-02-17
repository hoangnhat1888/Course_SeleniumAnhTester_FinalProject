package com.anhtester.Prac30_ScreenShoot_RecordVideo;

import com.anhtester.ExcelHelper.CaptureHelper;
import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DemoRecordVideo extends BaseTest {
    public void testBlogPage(){
        WebUI.openURL("https://anhtester.com");
        Assert.assertEquals(DriverManager.getDriver().getTitle(),"Anh Tester Automation Testing");

        WebUI.clickElement(By.xpath("//a[normalize-space()=''blog]"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("//a[normalize-space()=''Selenium]"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("(//div[@class='card-image'])/[1]"));
        WebUI.waitForPageLoaded();
        CaptureHelper.stopRecord();
    }


}
