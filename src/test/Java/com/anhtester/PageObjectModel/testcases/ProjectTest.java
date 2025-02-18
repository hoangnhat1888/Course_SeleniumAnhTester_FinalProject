package com.anhtester.PageObjectModel.testcases;

import com.anhtester.PageObjectModel.pages.*;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class ProjectTest extends BaseTest {
    LoginPage loginPage;// Khai bao doi tuong toan' cuc. thag nao dung thi thag do khoi tao
    ProjectPage projectPage;
    CommonPage commonPage;
    DashboardPage dashboardPage;

    @Test(priority = 1)
    public void verifyAddNewPrjSuccessfully(){
        String projectName = "Maison Do 1102";
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com","123456");
        projectPage = dashboardPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.enterDataAddProject(projectName,1);
        projectPage.checkProjectAfterAdded(projectName);
        System.out.println(projectName);
    }
}
