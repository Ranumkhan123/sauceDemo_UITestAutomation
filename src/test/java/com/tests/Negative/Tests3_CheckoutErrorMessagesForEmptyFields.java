package com.tests.Negative;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests3_CheckoutErrorMessagesForEmptyFields extends BaseTest {

    @BeforeClass
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }


    @Test(priority = 1)
    public void Login() {
        test = extent.createTest("Login Test");
        loginPage.openBrowser("https://www.saucedemo.com/");
        loginPage.loginApplication("standard_user", "secret_sauce");
    }

    @Test(priority = 2)
    public void AddtoCart() {
            test = extent.createTest("Add to Cart Test");
            addtocart.productAddtoCart();
        }

    @Test(priority = 3)
        public void CheckoutErrorMessagesForEmptyFields() {
            test = extent.createTest("Checkout Error Messages For Empty Fields Test");
            checkoutwithemptyfields.chekoutPage();
    }


    @Test(priority = 4)
    public void LogoutFromPage() {
        test = extent.createTest("Logout Test");
        logoutfromanypage.LogoutApplication();
    }









}
