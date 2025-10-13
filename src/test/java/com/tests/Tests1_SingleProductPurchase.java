package com.tests;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Tests1_SingleProductPurchase extends BaseTest {

    @Test(priority = 1, retryAnalyzer = utils.RetryAnalyzer.class)
    public void Login() {
        test = extent.createTest("Login Test");
        loginPage.openBrowser("https://www.saucedemo.com/");
        loginPage.loginApplication("standard_user", "secret_sauce");
    }

    @Test(priority = 2, retryAnalyzer = utils.RetryAnalyzer.class)
    public void AddtoCart() {
        test = extent.createTest("Add to Cart Test");
        addtocart.productAddtoCart();
    }

    @Test(priority = 3, retryAnalyzer = utils.RetryAnalyzer.class)
    public void CheckOut() {
        test = extent.createTest("Checkout Test");
        checkout.clickCheckout();
        checkout.checkoutInfoForm("Ranum", "Khan", "+92");
        checkout.completeCheckout();
        checkout.checkoutCompletePage();
    }

    @Test(priority = 4, retryAnalyzer = utils.RetryAnalyzer.class)
    public void Logout() {
        test = extent.createTest("Logout Test");
        logout.LogoutApplication();
    }
}
