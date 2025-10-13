package com.tests;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Test2_MultipleProductsPurchase extends BaseTest {


    @Test(priority = 1, retryAnalyzer = utils.RetryAnalyzer.class)
    public void Login() {
        loginPage.openBrowser("https://www.saucedemo.com/");
        loginPage.loginApplication("standard_user", "secret_sauce");
    }


@Test(priority = 2, retryAnalyzer = utils.RetryAnalyzer.class)
    public void AddProductstoCart(){
        addProductsToCart.addProductsToCart();
        addProductsToCart.goToCart();
}


    @Test(priority = 3, retryAnalyzer = utils.RetryAnalyzer.class)
    public void CheckOut() {
        checkout.clickCheckout();
        checkout.checkoutInfoForm("Ranum", "Khan", "+92");
        checkout.completeCheckout();
        checkout.checkoutCompletePage();
    }

    @Test(priority = 4, retryAnalyzer = utils.RetryAnalyzer.class)
    public  void Logout(){
        logout.LogoutApplication();

    }


}
