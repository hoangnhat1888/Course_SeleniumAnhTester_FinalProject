package com.anhtester.Prac27_PropertiesConfig.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends CommonPage {
    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;
    //Func xay dung cho tung Class Page
    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver = driver; // Nhận giá trí driver từ bên ngoài(BaseTest) khi khởi tạo
        new WebUI(driver); //Khoi tao Class WebUI de truyen gia tri driver
    }

    private By DashboardOptionBtn = By.xpath("//div[normalize-space()='Dashboard Options']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");
    private By sectionQuickStatistics = By.xpath("//div[@id='widget-top_stats']");

    public void clickBtnDashboardOptions(){
        WebUI.waitForPageLoaded(driver);
        driver.findElement(DashboardOptionBtn).click();
    }
    public void verifyCheckBoxQuickStatics(){
        //WebUI.waitForElementVisible(driver, checkboxQuickStatistics);
        WebUI.sleep(2);
        Assert.assertTrue(driver.findElement(checkboxQuickStatistics).isSelected(),"Failed - The value of Quick Statistics not match");
        Assert.assertTrue(driver.findElement(sectionQuickStatistics).isDisplayed(),"Failed - The section Quick Statistics not display");
    }
    public void verifyInvoicesAwaitingPayment(){
        // CHeck elm exist
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalInvoicesAwaitingPayment),"The Invoices Awaiting Payment doesn't exist");
        Assert.assertEquals(driver.findElement(totalInvoicesAwaitingPayment).getText(),"2 / 5","Failed - The Invoices Awaiting Payment the same value");
    }
    public void verifyTotalConvertedLeads(){
        // CHeck elm exist
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalConvertedLeads),"The Converted Leads doesn't exist");
        Assert.assertEquals(driver.findElement(totalConvertedLeads).getText(),"1 / 22","Failed - The Converted Leads not the same value");
    }
    public void verifyTotalProjectsInProgress(){
        // CHeck elm exist
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalProjectsInProgress),"The Projects In Progress doesn't exist");
        Assert.assertEquals(driver.findElement(totalProjectsInProgress).getText(),"0 / 6","Failed - The Projects In Progress not the same value");
    }
    public void verifyTotalTasksNotFinished(){
        // CHeck elm exist
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, totalTasksNotFinished),"The Tasks Not Finished doesn't exist");
        Assert.assertEquals(driver.findElement(totalTasksNotFinished).getText(),"8 / 9","Failed - The Tasks Not Finished not the same value");
    }


}
