package com.tests;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Test2_MultipleProductsPurchase extends BaseTest {


    @Test(priority = 1)
    public void Login() {
        loginPage.openBrowser("https://www.saucedemo.com/");
        loginPage.loginApplication("standard_user", "secret_sauce");
    }


@Test(priority = 2)
    public void AddProductstoCart(){
        addProductsToCart.addProductsToCart();
        addProductsToCart.goToCart();
}


    @Test(priority = 3)
    public void CheckOut() {
        checkout.clickCheckout();
        checkout.checkoutInfoForm("Ranum", "Khan", "+92");
        checkout.completeCheckout();
        checkout.checkoutCompletePage();
    }

    @Test(priority = 4)
    public  void Logout(){
        logout.LogoutApplication();

    }


}
