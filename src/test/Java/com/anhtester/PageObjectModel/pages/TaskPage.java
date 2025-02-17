package com.anhtester.PageObjectModel.pages;

import com.anhtester.ExcelHelper.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class TaskPage extends CommonPage {

    //Khai báo driver cục bộ trong chính class này
    private WebDriver driver;
    SoftAssert softassert = new SoftAssert();

    //Func xay dung cho tung Class Page
    public TaskPage(WebDriver driver){
        super(driver);
        this.driver = driver; // Nhận giá trí driver từ bên ngoài(BaseTest) khi khởi tạo
        new WebUI(driver); //Khoi tao Class WebUI de truyen gia tri driver
    }

    //Task page
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    public By btnQuickCreate = By.xpath("//i[@class='fa-regular fa-plus fa-lg']");
    public By btnAddNewTask = By.xpath("//li[contains(text(), 'Quick Create')]//following-sibling::li//a//span[contains(text(), 'Task')]");
    public By inputSubject = By.xpath("//div[@role='document']//input[@id='name']"); // need to be helped
    public By inputDesc = By.xpath("//textarea[@id='description']");
    public By inputHourlyRate = By.xpath("//input[@id=\"hourly_rate\"]");
    private By selectPriority = By.xpath("(//button[@title='Medium'])[1]");
    private By itemPriority = By.xpath("//span[normalize-space()='Low']");
    private By getItemPriority = By.xpath("//span[@class='trigger pointer manual-popover text-has-action']");
    private By getSubjectAfterAdded = By.xpath("//h4[@class='modal-title tw-flex tw-items-center']");
    public By btnSaveInTaskPopup = By.xpath("//button[contains(text(), 'Save')]");
    public By btnClose = By.xpath("//div[@class='modal-content data']//button[@aria-label='Close']");
    public By inputSearchProject = By.xpath("//input[@class='form-control input-sm']");
    public By firstItemProjectInTheProjectPage = By.xpath("//tbody/tr/td[2]/a");
    public By firstItemTaskInTheTaskPage = By.xpath("//tbody/tr/td[3]/a");
//    public By firstItemProjectInTheProject = By.xpath("//div[@id='task_view_description']");
    public By btnSort = By.xpath("//table/thead/tr/th[1]");
    public By inProgressTotal = By.xpath("//span[normalize-space()='In Progress']/preceding-sibling::span");
    private By alertMessageTask = By.xpath("//span[@class='alert-title']");

    public void selectPriority(String priorityName){
        WebUI.clickElement(selectPriority);
        WebUI.clickElement(By.xpath("/label[@for='" + priorityName + "']"));
    }

    //Handle when sys has got new Cus
    public String getInProgressTotal(){
        WebUI.waitForPageLoaded(driver);
        return driver.findElement(inProgressTotal).getText();
    }

    //Releated to Task Page
    public void enterNewTask(String taskName, int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx","TaskPageData");
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(btnQuickCreate);
        WebUI.clickElement(btnAddNewTask);
        WebUI.waitForPageLoaded(driver);
        WebUI.setText(inputSubject,excelHelper.getCellData("SUBJECT",row));
        WebUI.setText(inputHourlyRate,excelHelper.getCellData("HOURLYRATE",row));
        WebUI.clickElement(selectPriority);
        WebUI.waitForElementVisible(itemPriority);
        WebUI.clickElement(itemPriority);
        WebUI.sleep(1);
        WebUI.setText(inputDesc,excelHelper.getCellData("DESC",row));
        WebUI.sleep(2);
        WebUI.clickElement(btnSaveInTaskPopup);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(driver,getSubjectAfterAdded),"Fail - The Subject Name is not appeared");
        softassert.assertEquals(WebUI.getElementText(getSubjectAfterAdded),"Task Maison 1702 1\nIn Progress");
        WebUI.clickElement(btnClose);
        WebUI.clickElement(btnSort);
        WebUI.sleep(1);
        WebUI.clickElement(btnSort);
        Assert.assertTrue(WebUI.checkElementExist(firstItemProjectInTheProjectPage),"Fail - The Task Name is not appeared");
        softassert.assertAll();
    }
    public void verifySearchNewTaskAfterAdded(String taskName) {
        WebUI.waitForPageLoaded(driver);
        WebUI.clickElement(menuTasks);
        WebUI.waitForPageLoaded(driver);
        WebUI.setText(inputSearchProject,taskName);
        WebUI.sleep(10);
        //Check Task name appeared in the Table
        Assert.assertTrue(WebUI.checkElementExist(driver,firstItemTaskInTheTaskPage),"Fail - The Task Name is not appeared");
        WebUI.assertEquals(WebUI.getElementText(firstItemTaskInTheTaskPage), taskName, "\uD83D\uDC1E Fail !! - The Task Name is not exist ");
        //Assert.assertEquals(WebUI.getElementAttribute(getItemPriority,"value"),"Low","Fail ! The Priority name doesn't match");
        Assert.assertEquals(WebUI.getElementText(firstItemTaskInTheTaskPage), "Task Maison 1702 1","Fail ! The Priority name doesn't match");
    }
}
