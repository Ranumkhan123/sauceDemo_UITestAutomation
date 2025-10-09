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
import pages.*;
import utils.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AddtoCartPage addtocart;
    protected CheckoutPage checkout;
    protected LogoutPage logout;
    protected addProductsToCartPage addProductsToCart;
    protected static ExtentReports extent;
    protected static ExtentTest test;


    @BeforeSuite
    public void startReport() {
        extent = ExtentReportManager.getReportInstance();
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

        // ✅ Initialize all page objects AFTER driver is ready
        loginPage = new LoginPage(driver);
        addtocart = new AddtoCartPage(driver);
        checkout = new CheckoutPage(driver);
        logout = new LogoutPage(driver);
        addProductsToCart = new addProductsToCartPage(driver);
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
