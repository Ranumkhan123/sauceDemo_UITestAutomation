package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getReportInstance();
    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // Capture driver from test instance
    private WebDriver getDriver(ITestResult result) {
        Object instance = result.getInstance();
        try {
            return (WebDriver) instance.getClass().getField("driver").get(instance); // public driver in BaseTest
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        ExtentTest test = extent.createTest(testName + " [" + timeStamp + "]");
        testThread.set(test);

        // Assign browser category
        Object browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            testThread.get().assignCategory(browser.toString().toUpperCase());
            testThread.get().info("Running on Browser: " + browser);
        }

        // Store driver in thread
        driverThread.set(getDriver(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Passed Successfully");
        takeScreenshot(result, "Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        takeScreenshot(result, "Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private void takeScreenshot(ITestResult result, String status) {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            try {
                String screenshotBase64 = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BASE64);
                testThread.get().addScreenCaptureFromBase64String(
                        screenshotBase64, "Screenshot on " + status);
            } catch (Exception e) {
                testThread.get().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        } else {
            testThread.get().log(Status.WARNING, "Driver was null - screenshot not captured");
        }
    }

}
