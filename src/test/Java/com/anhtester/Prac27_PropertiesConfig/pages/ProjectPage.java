package com.anhtester.Prac27_PropertiesConfig.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProjectPage extends CommonPage {

    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;

    //Func xay dung cho tung Class Page
    public ProjectPage(WebDriver driver){
        super(driver);
        this.driver = driver; // Nhận giá trí driver từ bên ngoài(BaseTest) khi khởi tạo
        new WebUI(driver); //Khoi tao Class WebUI de truyen gia tri driver
    }

    //ELM
    public By headerPage = By.xpath("//span[normalize-space()='Projects Summary']");
    public By btnAddNewProject = By.xpath("//a[normalize-space()='New Project']");
    public By selectCustomer = By.xpath("//button[@data-id='clientid']");
    public By inputSearchCustomer = By.xpath("//button[@data-id='clientid']/following-sibling::div//input");
    public By itemCustomerName = By.xpath("//span[@class='text']");
    public void clickAddNewProject(){
        WebUI.clickElement(btnAddNewProject);

    }

    public void verifyCustomerAppearInSelectSection(String customerName){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(selectCustomer);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer,customerName);
        String itemCustomer = WebUI.getElementText(itemCustomerName);
        System.out.println("Customer in Select:" +itemCustomer);
        Assert.assertEquals(WebUI.getElementText(itemCustomerName),customerName,"Failed - Because of the Customer doesn't appeared in the Project page.");
    }

}
