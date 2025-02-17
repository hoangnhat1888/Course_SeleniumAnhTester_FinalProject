package com.anhtester.listeners;

import com.anhtester.ExcelHelper.CaptureHelper;
import com.anhtester.ExcelHelper.PropertiesHelper;
import com.anhtester.reports.ExtentReportManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    // Khi implements 1 cai' Interface thi phai Override lai cai Interface do

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("Star Testing" +result.getName());
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End Testing" +result.getName());
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }


    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info(" Starting Test cass"+ getTestName(result));

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

//        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
//            CaptureHelper.startRecord(getTestName(result));
//        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case: " + getTestName(result) + "failed");
        LogUtils.error(result.getThrowable());
        CaptureHelper.screenShoot(result.getName());

        //Extent Report
        ExtentTestManager.addScreenshot(getTestName(result));
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, getTestName(result) + " is failed.");

//        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
//            CaptureHelper.screenShoot(getTestName(result));
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        LogUtils.warn("Test cass: " + getTestName(result) + "skipped");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test cass: " + getTestName(result) + "passed.");
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, getTestName(result) + " is passed.");

//        if(PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")){
//            CaptureHelper.screenShoot(getTestName(result));
//        }
//        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
//            CaptureHelper.stopRecord();
//        }
    }
}