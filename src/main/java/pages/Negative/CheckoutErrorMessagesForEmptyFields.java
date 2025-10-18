package pages.Negative;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;



public class CheckoutErrorMessagesForEmptyFields {


    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public CheckoutErrorMessagesForEmptyFields(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }



    // Locators
    By checkoutButton = By.id("checkout");
    By chekoutPage = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By firstNameField = By.id("first-name");
    By continueBtn = By.id("continue");
    By errorMessage = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]");



    public void chekoutPage(){

        WebElement clickCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        clickCheckout.click();

        WebElement checkoutPageDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(chekoutPage));
        Assert.assertTrue(checkoutPageDisplayed.isDisplayed(), "Checkout page is not displayed");

        if(checkoutPageDisplayed.isDisplayed()){
            System.out.println();
            System.out.println("Checkout page is displayed");
        }else {
            System.out.println();
            System.out.println("Checkout page is not displayed");
        }

        //Add checkout fields

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstName.sendKeys("Ranum");



        //Click continue button
        WebElement clickContinueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        clickContinueBtn.click();

        //Verify error message
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String expectedErrorMessage = "Error: Last Name is required";
        String actualErrorMessage = errorMsg.getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match");

        if(actualErrorMessage.equals(expectedErrorMessage)){
            System.out.println();
            System.out.println("Error message is displayed: " + actualErrorMessage);
        }else {
            System.out.println();
            System.out.println("Error message is not displayed");
        }



    }












}
