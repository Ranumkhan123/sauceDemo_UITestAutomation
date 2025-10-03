package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.*;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AddtoCartPage addtocart;
    protected CheckoutPage checkout;
    protected LogoutPage logout;
    protected addProductsToCartPage addProductsToCart;

    @BeforeTest
    public void setUp() {
        // Browser setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Page objects initialize
        loginPage = new LoginPage(driver);
        addtocart = new AddtoCartPage(driver);
        checkout = new CheckoutPage(driver);
        logout = new LogoutPage(driver);
        addProductsToCart = new addProductsToCartPage(driver);
    }

    @AfterTest
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
