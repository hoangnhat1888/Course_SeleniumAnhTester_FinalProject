package com.anhtester.common;

import com.anhtester.ExcelHelper.PropertiesHelper;
import com.anhtester.drivers.DriverManager;
import com.anhtester.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})

    public void createDriver(@Optional("chrome") String browser)
    {
        if(PropertiesHelper.getValue("BROWSER").isEmpty() || PropertiesHelper.getValue("BROWSER") == null){
            driver = setupDriver(PropertiesHelper.getValue("BROWSER"));
        }else {
            driver = setupDriver(browser);
        }
        DriverManager.setDriver(driver);// Set Driver into ThreadLocal
    }

    public WebDriver setupDriver(String browserName) {
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
//            case "edge":
//                driver = initEdgeDriver();
//                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver();
        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver();
//        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
//    public void closeDriver(ITestResult iTestResult) {
//        if (iTestResult.getStatus() == ITestResult.FAILURE){
//            Capture_Helper.screenShoot(iTestResult.getTestName());
//        }
//        if(driver != null) {driver.quit();}
////        DriverManager.quit();
//    }
}
