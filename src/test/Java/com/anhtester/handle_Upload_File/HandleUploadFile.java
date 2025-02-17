package com.anhtester.handle_Upload_File;

import com.anhtester.ExcelHelper.SystemHelper;
import com.anhtester.common.BaseTest;
import com.anhtester.keywords.WebUI;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HandleUploadFile extends BaseTest {
    @Test
    public void testUploadFileWithSendKeys() throws InterruptedException {
        WebUI.openURL("https://cgi-lib.berkeley.edu/ex/fup.html");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2000);

        By inputFileUpload = By.xpath("//input[@name='upfile']");
        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\paris.jpg";

        WebUI.setText(inputFileUpload,filePath);
//        driver.findElement(inputFileUpload).sendKeys(System.getProperty("user.dir") + "/datatest/Selenium4_Upload.png");

        WebUI.sleep(4000);
        WebUI.clickElement(By.xpath("//input[@value='Press']"));
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(By.xpath("//h1[normalize-space()='File Upload Result']")));

    }
}
