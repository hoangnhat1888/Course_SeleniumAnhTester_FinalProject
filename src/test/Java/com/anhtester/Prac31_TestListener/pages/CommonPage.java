package com.anhtester.Prac31_TestListener.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage {
    private WebDriver driver;
    //Func xay dung cho tung Class Page
    public CommonPage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }
    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    public By menuSales = By.xpath("//li[@class='menu-item-sales']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By itemNotifications = By.xpath("//a[contains(@class,'notifications-icon')]");
    public CustomerPage clickMenuCustomer(){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(menuCustomers);
        return new CustomerPage(driver);
    }

    public DashboardPage clickMenuDashBoard(){
        WebUI.waitForPageLoaded(driver);
        driver.findElement(menuDashboard).click();
        return new DashboardPage(driver);
    }

    public ProjectPage clickMenuProjects(){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(menuProjects);
        return new ProjectPage(driver);
    }

}
