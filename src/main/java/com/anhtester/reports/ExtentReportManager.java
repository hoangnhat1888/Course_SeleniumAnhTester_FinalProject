package com.anhtester.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("exports/ExtentReport/ExtentReport.html");
        reporter.config().setReportName("Extent Report | Anh Nhat Tester");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Anh Nhat Tester");
        extentReports.setSystemInfo("Author", "Anh Nhat Tester");
        extentReports.setSystemInfo("Version", "1.0.1");

        return extentReports;
    }

}
