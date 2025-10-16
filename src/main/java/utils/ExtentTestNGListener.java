package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = getDriver(result);
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName() + "_PASS");
        testThread.get().pass("Test passed ✅");
        if (screenshotPath != null) {
            testThread.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName() + "_FAIL");
        testThread.get().fail(result.getThrowable());
        if (screenshotPath != null) {
            testThread.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }


    private WebDriver getDriver(Object testClass) {
        try {
            return (WebDriver) testClass.getClass().getSuperclass().getDeclaredField("driver").get(testClass);
        } catch (Exception e1) {
            try {
                return (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

}
