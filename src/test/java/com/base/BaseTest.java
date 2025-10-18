package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pages.LogoutfromAnyPage;
import pages.Negative.CheckoutErrorMessagesForEmptyFields;
import pages.Negative.InvalidLogin;
import pages.Negative.InvalidPassword;
import pages.Positive.*;
import utils.ExtentListener;


@Listeners(ExtentListener.class)
public class BaseTest {


    public WebDriver driver;
    protected LoginPage loginPage;
    protected AddtoCartPage addtocart;
    protected CheckoutPage checkout;
    protected LogoutPage logout;
    protected addProductsToCartPage addProductsToCart;
    protected productVerification productverification;
    protected LogoutfromHomePage logoutfromhomepage;
    protected CartItemsVerification cartitemsverification;
    protected MultipleCartItemVerification multiplecartitemverification;
    protected InvalidLogin invalidlogin;
    protected InvalidPassword invalidpassword;
    protected CheckoutErrorMessagesForEmptyFields checkoutwithemptyfields;
    protected LogoutfromAnyPage logoutfromanypage;

    public static ExtentReports extent;
    public static ExtentTest test;


    @BeforeSuite
    public void setupExtent() {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/reports/ExtentReport_" + timestamp + ".html";

        com.aventstack.extentreports.reporter.ExtentSparkReporter spark = new com.aventstack.extentreports.reporter.ExtentSparkReporter(reportPath);
        extent = new com.aventstack.extentreports.ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Ranum Khan");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Application", "SauceDemo");
    }



    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Initialize all page objects AFTER driver is ready
        loginPage = new LoginPage(driver);
        addtocart = new AddtoCartPage(driver);
        checkout = new CheckoutPage(driver);
        logout = new LogoutPage(driver);
        addProductsToCart = new addProductsToCartPage(driver);
        productverification = new productVerification(driver);
        logoutfromhomepage = new LogoutfromHomePage(driver);
        cartitemsverification = new CartItemsVerification(driver);
        multiplecartitemverification = new MultipleCartItemVerification(driver);
        invalidlogin = new InvalidLogin(driver);
        invalidpassword = new InvalidPassword(driver);
        checkoutwithemptyfields = new CheckoutErrorMessagesForEmptyFields(driver);
        logoutfromanypage = new LogoutfromAnyPage(driver);



    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @AfterSuite
    public void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
