package pages.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutfromHomePage {


    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public LogoutfromHomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By checkoutCompleteHeader = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By backhomeBtn = By.id("back-to-products");
    By productPage = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By menuBtn = By.xpath("//*[@id=\"menu_button_container\"]/div/div[1]/div");
    By logoutBtn = By.cssSelector("#logout_sidebar_link");
    By loginPage = By.id("login_button_container");
    By loginpageHeader = By.xpath("//*[@id=\"root\"]/div/div[1]");


    public void LogoutApplication(){

        //go to menu
        WebElement clickMenuBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(menuBtn));
        clickMenuBtn.click();

        //click on logut btn
        WebElement clickLogoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));
        clickLogoutBtn.click();

        //verify application is logged out
        WebElement verifyLoginPageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage));


        WebElement verifyLoginPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(loginpageHeader));

        String loginPageHeaderExpectedText = verifyLoginPageHeader.getText();
        String loginPageHeaderActualText = "Swag Labs";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(loginPageHeaderActualText, loginPageHeaderExpectedText, "Login Page not opened");
            // This line will only run if assertion passes
            System.out.println();
            System.out.println("The Application is logged out...!");
            System.out.println("Login Page is visible");


        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Product Page not opened");
            Assert.fail("Unexpected error while verifying cart item.");
        }



    }






}
