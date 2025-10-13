package com.tests;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Tests1_EndtoEndFlow_SingleProduct extends BaseTest {

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
    public void CheckOut() {
        test = extent.createTest("Checkout Test");
        checkout.clickCheckout();
        checkout.checkoutInfoForm("Ranum", "Khan", "+92");
        checkout.completeCheckout();
        checkout.checkoutCompletePage();
    }

    @Test(priority = 4)
    public void Logout() {
        test = extent.createTest("Logout Test");
        logout.LogoutApplication();
    }
}
