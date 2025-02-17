package com.anhtester.Prac27_PropertiesConfig.testcases;

import com.anhtester.Prac27_PropertiesConfig.pages.*;
import com.anhtester.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    CommonPage commonPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    ProjectPage projectPage;
    @Test(description = "Verify CustomerName and ProjectName")
    public void testAddNewCustomer(){

        String customerName = "A3 MaisonDo Maison Mergiela";
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM("admin@example.com","123456");
        customerPage = dashboardPage.clickMenuCustomer();

        int totalCustomerBeforeAddNew = Integer.parseInt(customerPage.getTotalCustomer());// Ép kiểu bởi vì getTotalCustomer sẽ get ra chuỗi ở dưới Assert
        System.out.println("Total Customer Before Add New:" + totalCustomerBeforeAddNew);
        customerPage.clickAddNewButton();
        customerPage.enterDataAddNewCustomer(customerName);
        customerPage.checkCustomerInTheTableList(customerName);
        System.out.println("Total Customer After Add New:" + totalCustomerBeforeAddNew);
        Assert.assertEquals(customerPage.getTotalCustomer(), String.valueOf((totalCustomerBeforeAddNew + 1)),"Failed - Because the total customer between Before and After add both are not match");
        customerPage.checkCustomerDetails(customerName);
        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.verifyCustomerAppearInSelectSection(customerName);
    }
}
