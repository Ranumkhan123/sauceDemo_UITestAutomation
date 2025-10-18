package com.tests.Negative;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests1_InvalidLogin extends BaseTest {


    @BeforeClass
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test(priority = 1)
    public void InvalidLogin() {
        test = extent.createTest("Login Test");
        invalidlogin.openBrowser("https://www.saucedemo.com/");
        invalidlogin.loginApplication("standard_user", "sauce");
    }

}
