package com.tests.Negative;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Tests1_InvalidLogin extends BaseTest {


    @Test(priority = 1)
    public void InvalidLogin() {
        test = extent.createTest("Login Test");
        invalidlogin.openBrowser("https://www.saucedemo.com/");
        invalidlogin.loginApplication("standard_user", "sauce");
    }

}
