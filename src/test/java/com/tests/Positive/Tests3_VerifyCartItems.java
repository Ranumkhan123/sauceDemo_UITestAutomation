package com.tests.Positive;

import com.base.BaseTest;
import org.testng.annotations.Test;

public class Tests3_VerifyCartItems extends BaseTest {


    @Test(priority = 1)
    public void Login() {
        loginPage.openBrowser("https://www.saucedemo.com/");
        loginPage.loginApplication("standard_user", "secret_sauce");
    }

    @Test(priority = 2)
    public void CartCount() {
        cartitemsverification.addItemsToCart();
        cartitemsverification.ClickonCart();
        cartitemsverification.CartVerification();

    }



    @Test(priority = 3)
    public  void Logout(){
        logoutfromhomepage.LogoutApplication();

    }
}
