package com.anhtester.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    //Class DriverManager này dùng để lưu session của driver cho vào ThreadLocal của Java để quản lý các luồng driver được khởi tạo.

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }
    // Lay Driver from ThreadLocal to be use
    public static WebDriver getDriver() {
        return driver.get();
    }
    // set 1 value Driver into ThreadLocal
    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }
    // Clear sesion that we already run previoustly
    public static void quit() {
        DriverManager.driver.get().quit();
        driver.remove();
    }
}
