package utils;

import com.aventstack.extentreports.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListener implements ITestListener {

    public static ExtentReports extent = ExtentReportManager.getReportInstance();
    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver != null) attachScreenshot(driver, result, "PASS");
        testThread.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver != null) attachScreenshot(driver, result, "FAIL");
        testThread.get().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Helper: get WebDriver from test class
    private WebDriver getDriver(ITestResult result) {
        Object testClass = result.getInstance();
        try {
            return (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);
        } catch (Exception e) {
            return null;
        }
    }

    // Helper: take screenshot
    private void attachScreenshot(WebDriver driver, ITestResult result, String status) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + result.getMethod().getMethodName() + "_" + status + "_" + timestamp + ".png";
            Files.createDirectories(new File(path).getParentFile().toPath());
            Files.copy(src.toPath(), new File(path).toPath());
            testThread.get().addScreenCaptureFromPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
