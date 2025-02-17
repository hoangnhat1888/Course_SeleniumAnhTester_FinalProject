package com.anhtester.PageObjectModel.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PopUpAddNewTask extends CommonPage{

    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;

    //Func xay dung cho tung Class Page
    public PopUpAddNewTask(WebDriver driver){
        super(driver);
        this.driver = driver; // Nhận giá trí driver từ bên ngoài(BaseTest) khi khởi tạo
        new WebUI(driver); //Khoi tao Class WebUI de truyen gia tri driver
    }

}
