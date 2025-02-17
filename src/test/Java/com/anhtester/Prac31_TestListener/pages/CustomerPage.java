package com.anhtester.Prac31_TestListener.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomerPage extends CommonPage {
    private WebDriver driver;

    public CustomerPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        new WebUI(driver); //Khoi tao Class WebUI de truyen gia tri driver
    }

    //ELM
    private By btnAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By headerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@placeholder='Search...']");
    private By firstItemCompanyInTheCustomerSummaryComponent = By.xpath("/html/body/div[3]/div/div/div/div[3]/div/div[3]/div/div/div[4]/table/tbody/tr[1]/td[3]/a");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVat = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By selectGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By inputGroups = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By selectLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemVietnam = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By selectCountry = By.xpath("//button[@data-id='country']");
    private By inputCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By btnSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By alertMessage = By.xpath("//span[@class='alert-title']");
    private By customerTotal = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");


    //Ham xu ly trang Cus
    public void clickAddNewButton(){
        WebUI.clickElement(btnAddNewCustomer);
    }

    public String getTotalCustomer(){
        WebUI.waitForPageLoaded(driver);
        return driver.findElement(customerTotal).getText();
    }

    public void selectLanguage(String languageName){
        WebUI.clickElement(selectLanguage);
        WebUI.clickElement(By.xpath("//span[normalize-space()='" + languageName + "']"));
    }
    public void enterDataAddNewCustomer(String customerName){
        WebUI.setText(inputCompany,"A3 MaisonDo Maison Mergiela");
        WebUI.setText(inputVat,"19001000");
        WebUI.setText(inputPhone,"0905555999");
        WebUI.setText(inputWebsite,"google.com");
        WebUI.clickElement(selectGroups);
        WebUI.sleep(1);
        WebUI.setText(inputGroups,"VIP");
        WebUI.sleep(1);
        WebUI.setKey(inputGroups, Keys.ENTER);
        WebUI.sleep(1);
        WebUI.clickElement(selectGroups);
        selectLanguage("German");
        WebUI.sleep(2);

        WebUI.clickElement(selectCountry);
        WebUI.sleep(1);
        WebUI.setText(inputCountry,"Australia");
        WebUI.sleep(1);
        WebUI.setKey(selectCountry, Keys.ENTER);
        WebUI.sleep(1);

        WebUI.setText(inputAddress,"Maison Margiela Tester");
        WebUI.setText(inputCity,"Paris");
        WebUI.setText(inputState,"Stadium");
        WebUI.setText(inputZip,"700000");
        WebUI.clickElement(btnSave);
        WebUI.waitForPageLoaded(driver);

        Assert.assertTrue(WebUI.checkElementExist(driver, alertMessage));
        WebUI.sleep(1);
        Assert.assertEquals(driver.findElement(alertMessage).getText().trim(),"Customer added successfully.");
    }
    public void checkCustomerInTheTableList(String customerName){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(menuCustomers);
        WebUI.waitForPageLoaded(driver);
        WebUI.setText(inputSearchCustomer, customerName);
        WebUI.sleep(10);
        //Check Customer name appeared in the Table
        Assert.assertTrue(WebUI.checkElementExist(driver, firstItemCompanyInTheCustomerSummaryComponent),"Fail - The Customer Name is not appeared");
        //Assert.assertEquals(WebUI.getElementText(firstItemCompanyInTheCustomerSummaryComponent), customerName,"\uD83D\uDC1E Fail !! - The Customer Name is not exist ");

        WebUI.assertEquals(WebUI.getElementText(firstItemCompanyInTheCustomerSummaryComponent), customerName, "\uD83D\uDC1E Fail !! - The Customer Name is not exist ");

    }

    public void checkCustomerDetails(String customerName){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(firstItemCompanyInTheCustomerSummaryComponent);
        WebUI.waitForPageLoaded(driver);
        // Check customer details in Customer Detail page
        // Value in the thẻ Input tag must be used 'getAttribute' func/ Value in the Button tag must be used 'getTitle' func/ Value in the DIV tag must be used 'getText' func
        WebUI.assertEquals(WebUI.getElementAttribute(inputCompany,"value"), customerName,"Fail ! The Company name doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputVat,"value"), "19001000","Fail ! The VAT doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputPhone,"value"), "0905555999", "Fail ! The Phone doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputZip,"value"), "700000","Fail ! The Zip doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputCity,"value"), "Paris","Fail ! The City name doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputAddress,"value"), "Maison Margiela Tester","Fail ! The Address name doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputWebsite,"value"), "google.com","Fail ! The Website name doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(selectGroups,"title"),"VIP", "Fail ! The Group name doesn't match");
        WebUI.assertEquals(WebUI.getElementAttribute(selectLanguage,"title"),"German", "Fail ! The Language name doesn't match");
    }
}
