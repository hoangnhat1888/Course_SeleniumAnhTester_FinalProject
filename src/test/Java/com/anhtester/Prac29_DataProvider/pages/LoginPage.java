package com.anhtester.Prac29_DataProvider.pages;

import com.anhtester.constants.ConfigData;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends CommonPage {
    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;

    //Hàm tao/xay dung cho tung Class Page = Constructor có đối số
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver; // Nhận giá trí driver từ bên ngoài(BaseTest) khi khởi tạo
        new WebUI(driver);    // Khoi tao Class WebUI de truyen gia tri Driver
    }

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert alert-danger')]");
    private By avatarUserprofile = By.xpath("//li[@class='icon header-user-profile']");
    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    //Khai bao ham xu ly thuoc trang Login
    public void enterEmail(String email){
        WebUI.setText(inputEmail,email);
    }
    public void enterPassword(String password){
        WebUI.setText(inputPassword,password);
    }
    public void clickLogin(){
        WebUI.clickElement(buttonLogin);
        //driver.findElement(buttonLogin).click();
    }

    public DashboardPage loginCRM(String email, String pw){
        //driver.get(ConfigData.URL);
        WebUI.openURL(ConfigData.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(pw);
        clickLogin();

        return new DashboardPage(driver);
    }

    public void verifyLoginSuccess() {
        WebUI.waitForPageLoaded();
        //Assert.assertTrue(WebUI.checkElementExist(avatarUserprofile),"Failed - Unable to Login");
        Assert.assertEquals(driver.getCurrentUrl(),"https://crm.anhtester.com/admin/authentication","Failed - Unable to get the current URL");
        Assert.assertTrue(driver.findElement(menuDashboard).isDisplayed(),"Failed - Unable to find the Dashboard Menu");
    }
    public void verifyLoginFailed(String expectedMessage) {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(driver, errorMessage),"Failed - The error message is not display");
        Assert.assertEquals(WebUI.getElementText(errorMessage),"expectedMessage","Failed - The content of error message both are not match");
    }

}
