package com.anhtester.PageObjectModel.pages;

import com.anhtester.ExcelHelper.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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


    //ELM Project Add new project
    public By headerPage = By.xpath("//span[normalize-space()='Projects Summary']");
    public By btnAddNewProject = By.xpath("//a[normalize-space()='New Project']");
    public By inputProjectName = By.xpath("//input[@id='name']");
    public By selectCustomer = By.xpath("//button[@data-id='clientid']");
    public By inputSearchCustomer = By.xpath("//input[@placeholder='Type to search...']");
    public By itemCustomerName = By.xpath("//a[@id='bs-select-6-0']");
    public By inputRatePerHour = By.xpath("//input[@id='project_rate_per_hour']");
    public By btnSaveNewProject = By.xpath("//button[normalize-space()='Save']");
    public By inputEstimatedHours = By.xpath("//input[@id='estimated_hours']");
    public By titlePrjAfterAdded = By.xpath("//button[@data-id='project_top']");
    private By alertMessage = By.xpath("//span[@class='alert-title']");

    private By selectPriority = By.xpath("//*[@id=\"_task_modal\"]/div/div/div[2]/div/div/div[8]/div[3]/div/div/button");
    public By inputSearchProject = By.xpath("//input[@class='form-control input-sm']");
    public By firstItemProjectInTheProjectPage = By.xpath("//tbody/tr/td[2]/a");
    public By firstItemProjectInTheProject = By.xpath("//div[@id='task_view_description']");
    public By btnSort = By.xpath("//table/thead/tr/th[1]");
    private By alertMessageTask = By.xpath("//span[@class='alert-title']");


    // Func handle
    public void clickAddNewProject(){
        WebUI.clickElement(btnAddNewProject);
    }

//    public void selectCustomer(String customer){
//        WebUI.clickElement(selectCustomer);
//        WebUI.clickElement(By.xpath("//span[normalize-space()='" + customer + "']"));
//    }

    public void selectPriority(String priorityName){
        WebUI.clickElement(selectPriority);
        WebUI.clickElement(By.xpath("/label[@for='" + priorityName + "']"));
    }

    //Releated to Cus
    public void verifyCustomerAppearInSelectSection(String customerName){
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(selectCustomer);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer,customerName);
        String itemCustomer = WebUI.getElementText(itemCustomerName);
        System.out.println("Customer in Select:" +itemCustomer);
        Assert.assertEquals(WebUI.getElementText(itemCustomerName),customerName,"Failed - Because of the Customer doesn't appeared in the Project page.");
    }

    //Releated to Project
    public void enterDataAddProject(String projectName,int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","ProjectPageData");
        WebUI.setText(inputProjectName,excelHelper.getCellData("PROJECTNAME",row));
        WebUI.clickElement(selectCustomer);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer,excelHelper.getCellData("SEARCHCUSTOMER",row));
        WebUI.sleep(3);
        WebUI.clickElement(itemCustomerName);
        WebUI.sleep(2);
        WebUI.setText(inputRatePerHour,excelHelper.getCellData("RATEPERHOUR",row));
        WebUI.setText(inputEstimatedHours,excelHelper.getCellData("ESTIMATEDHOURS",row));
        WebUI.clickElement(btnSaveNewProject);
        WebUI.waitForPageLoaded(driver);
        Assert.assertTrue(WebUI.checkElementExist(driver, alertMessage));
        WebUI.sleep(2);
        Assert.assertEquals(driver.findElement(alertMessage).getText().trim(),"Project added successfully.");
    }
    public void checkProjectAfterAdded(String projectName) {
        WebUI.waitForPageLoaded(driver);
//        System.out.println(WebUI.getElementText(titlePrjAfterAdded));
//        Assert.assertEquals(WebUI.getElementText(titlePrjAfterAdded),inputProjectName,"Failed - Because of the Project doesn't match with the name that has been saved previously.");
        WebUI.clickElement(menuProjects);
        WebUI.waitForPageLoaded(driver);
        WebUI.setText(inputSearchProject,projectName);
        WebUI.sleep(10);
        //Check Prject name appeared in the Table
        Assert.assertTrue(WebUI.checkElementExist(driver,firstItemProjectInTheProjectPage),"Fail - The Customer Name is not appeared");
        WebUI.assertEquals(WebUI.getElementText(firstItemProjectInTheProjectPage), projectName, "\uD83D\uDC1E Fail !! - The Customer Name is not exist ");
    }
}
