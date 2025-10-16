package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutfromAnyPage {

    public WebDriver driver;
    public WebDriverWait wait;


    // Constructor
    public LogoutfromAnyPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By menuBtn = By.xpath("//*[@id=\"menu_button_container\"]/div/div[1]/div");
    By logoutBtn = By.cssSelector("#logout_sidebar_link");
    By loginPage = By.id("login_button_container");
    By loginpageHeader = By.xpath("//*[@id=\"root\"]/div/div[1]");


    public void LogoutApplication(){

        //go to menu btn
        WebElement clickMenuBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(menuBtn));
        clickMenuBtn.click();

        //click on logout btn
        WebElement clickLogoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));
        clickLogoutBtn.click();

        //verify application is logged out
        WebElement verifyLoginPageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage));
        Assert.assertTrue(verifyLoginPageDisplayed.isDisplayed(), "Login page is not displayed");
        System.out.println();
        System.out.println("Login page is displayed");

        //verifying login page header
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


        } catch (Exception e) {
            // Handle any unexpected errors
            Assert.fail("Unexpected error while verifying cart item.");
            System.out.println();
            System.out.println("The Application is not logged out...!");
        }



    }

}
