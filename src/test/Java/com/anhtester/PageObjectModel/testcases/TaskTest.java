package com.anhtester.PageObjectModel.testcases;

import com.anhtester.PageObjectModel.pages.*;
import com.anhtester.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class TaskTest extends BaseTest {
    LoginPage loginPage;// Khai bao doi tuong toan' cuc. thag nao dung thi thag do khoi tao
    ProjectPage projectPage;
    DashboardPage dashboardPage;
    TaskPage taskPage;

    @Test(priority = 1)
    public void verifyNewTask(){
        String taskName = "Task Maison 1702 1";
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com","123456");
        projectPage = dashboardPage.clickMenuProjects();
        taskPage = projectPage.clickMenuTasks();
        int totalTaskInProgress = Integer.parseInt(taskPage.getInProgressTotal());// Ép kiểu bởi vì totalTaskInProgress sẽ get ra chuỗi ở dưới Assert
        taskPage.enterNewTask(taskName,1);
        System.out.println("Total InProgress After Add New Task:" + totalTaskInProgress);
        Assert.assertEquals(taskPage.getInProgressTotal(), String.valueOf((totalTaskInProgress)),
                "Failed - Because the total Task InProgress between Before and After add both are not match");
        taskPage.verifySearchNewTaskAfterAdded(taskName);
    }
}
