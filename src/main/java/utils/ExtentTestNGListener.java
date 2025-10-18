package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class ExtentTestNGListener implements ITestListener {

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
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private WebDriver getDriver(ITestResult result) {
        try {
            Object testClass = result.getInstance();
            Field driverField = testClass.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
