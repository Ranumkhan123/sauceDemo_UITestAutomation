package com.tests.Negative;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Tests2_LoginwithInvalidPassword extends BaseTest {


    @Test(priority = 1)
    public void InvalidLogin() {
        test = extent.createTest("Login Test");
        invalidpassword.openBrowser("https://www.saucedemo.com/");
        invalidpassword.loginApplication("standard", "sauce");
    }

}
