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

public class ExtentTestNGListener implements ITestListener, IInvokedMethodListener {

    private static ExtentReports extent = ExtentReportManager.getReportInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // This method will be called before every test method
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Add timestamp to test name for clarity
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        ExtentTest test = extent.createTest(testName + "  [" + timeStamp + "]");
        testThread.set(test);

        // Log browser info (if available as parameter)
        Object browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            testThread.get().assignCategory(browser.toString().toUpperCase());
            testThread.get().info("Running on Browser: " + browser);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "✅ Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        WebDriver driver = driverThread.get();
        if (driver != null) {
            try {
                // Capture screenshot as Base64
                String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                testThread.get().addScreenCaptureFromBase64String(screenshot, "Screenshot on Failure");
            } catch (Exception e) {
                testThread.get().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Optional: capture driver from test classes dynamically
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        Object instance = testResult.getInstance();
        try {
            WebDriver driver = (WebDriver) instance.getClass().getDeclaredField("driver").get(instance);
            driverThread.set(driver);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Remove driver after test completes
        driverThread.remove();
    }
}
