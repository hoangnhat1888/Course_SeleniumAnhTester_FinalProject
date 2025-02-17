package com.anhtester.Prac29_DataProvider.testcases;

import com.anhtester.Prac29_DataProvider.pages.DashboardPage;
import com.anhtester.Prac29_DataProvider.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;// Khai bao doi tuong toan' cuc. thag nao dung thi thag do khoi tao
    DashboardPage dashboardPage;
    @Test(priority = 1)
    public void verifyCheckSelectionQuickStaticsDisplay(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com","123456");// Khi ham Login nó Login thành công thì nó sẽ khởi to mới thằng 'dashboardPage' còn trong mặt code thì

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.clickBtnDashboardOptions();
        dashboardPage.verifyCheckBoxQuickStatics();

    }
    @Test(priority = 2)
    public void verifyCheckTotalSelectionQuickStatics(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com","123456");

//        dashboardPage = new DashboardPage(driver);
        dashboardPage.verifyInvoicesAwaitingPayment();
        dashboardPage.verifyTotalConvertedLeads();
        dashboardPage.verifyTotalProjectsInProgress();
        dashboardPage.verifyTotalTasksNotFinished();
    }

}
