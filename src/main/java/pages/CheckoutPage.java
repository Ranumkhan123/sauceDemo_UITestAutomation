package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckoutPage {

    // Constructor
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    WebDriver driver;
    WebDriverWait wait;

    //Locators
    By cartpageHeaderVerify = By.className("header_secondary_container");
    By checkoutBtn = By.id("checkout");
    By checkoutPageHeaderVerify = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By checkoutOverviewHeaderVerify = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By paymentInfoSummary = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]");
    By finishBtn = By.id("finish");
    By checkoutCompleteHeaderVerify = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By orderPlacedText = By.className("complete-header");
    By orderPlacedDetailedText = By.className("complete-text");

    public void clickCheckout() {

        //Click on CheckOut Button
        WebElement clickCheckoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        clickCheckoutBtn.click();

        //Verify Checkout Page Header
        WebElement cartHeaderVerify = wait.until(ExpectedConditions.visibilityOfElementLocated(cartpageHeaderVerify));
        String expectedText = cartHeaderVerify.getText();
        String actualText = "Checkout: Your Information";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(actualText, expectedText, "Checkout Page not opened");
            // This line will only run if assertion passes
            System.out.println("Expected Text: " + expectedText + " is same as Actual Text" + " " + actualText);

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Checkout Page not opened");
            Assert.fail("Unexpected error while verifying cart item.");
        }

    }

    public void checkoutInfoForm(String firstname, String lastname, String postalcode){
        //verify firstname, last name and postal code textbox

        WebElement firstnameText = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        WebElement lastnameText = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        WebElement postalcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));

        firstnameText.sendKeys(firstname);
        lastnameText.sendKeys(lastname);
        postalcodeText.sendKeys(postalcode);

        //verify form fields

        //firstname
        String firstnameactualValue = firstnameText.getAttribute("value");
        Assert.assertEquals(firstnameactualValue, "Ranum", "First Name not filled correctly!");
        //lastname
        String lastnameactualValue = lastnameText.getAttribute("value");
        Assert.assertEquals(lastnameactualValue, "Khan", "Last Name not filled correctly!");
        //postal code
        String postalcodeactualValue = postalcodeText.getAttribute("value");
        Assert.assertEquals(postalcodeactualValue, "+92", "Postal Code not filled correctly!");

        //Print info
        System.out.println();
        System.out.println("Checkout: Your Information: ");
        System.out.println("First Name: " + firstname);
        System.out.println("Last Name: " + lastname);
        System.out.println("Postal Code: " + postalcode);

    }

    public void completeCheckout(){

        //click on continue button
        WebElement clickContinueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        clickContinueBtn.click();

        //chekout overview page
        WebElement checkoutOverviewHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutOverviewHeaderVerify));

        String checkoutOverviewexpectedText = checkoutOverviewHeader.getText();
        String checkoutOverviewactualText = "Checkout: Overview";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(checkoutOverviewactualText, checkoutOverviewexpectedText, "Checkout Overview Page not opened");
            // This line will only run if assertion passes
            System.out.println("Checkout Overview Page");
            System.out.println("Expected Text: " + checkoutOverviewexpectedText + " is same as Actual Text: " + " " + checkoutOverviewactualText);

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Checkout Overview Page not opened");
            Assert.fail("Unexpected error while verifying cart item.");
        }

        //verify payment info
        WebElement paymentInfoVerify = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoSummary));
        paymentInfoVerify.getText();

        //click finish button
        WebElement clickFinishBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn));
        clickFinishBtn.click();

        //verify checkout complete
        WebElement checkoutCompleteHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompleteHeaderVerify));

        String checkoutCompleteexpectedText = checkoutCompleteHeader.getText();
        String checkoutCompleteactualText = "Checkout: Complete!";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(checkoutCompleteactualText, checkoutCompleteexpectedText, "Checkout Complete Page not opened");
            // This line will only run if assertion passes
            System.out.println();
            System.out.println("Checkout Complete Page");
            System.out.println("Expected Text: " + checkoutCompleteexpectedText + " is same as Actual Text: " + " " + checkoutCompleteactualText);

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Checkout Complete Page not opened");
            Assert.fail("Unexpected error while verifying cart item.");
        }

    }

    public void checkoutCompletePage(){

        //verify finish details
        WebElement checkoutCompletePageText = wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedText));

        String orderPlacedExpectedText = checkoutCompletePageText.getText();
        String orderPlacedActualText = "Thank you for your order!";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(orderPlacedActualText, orderPlacedExpectedText, "Checkout Finish Page not opened!");
            System.out.println();
            System.out.println("Thank You message verified successfully!");


        } catch (AssertionError e) {
            System.out.println("Checkout Complete Page not opened. Assertion failed: " + e.getMessage());
            Assert.fail("Checkout complete text verification failed!");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Unexpected error while verifying checkout complete page!");
        }

        //Verify order details

        WebElement verifyOrderPlacedDetailedText = wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedDetailedText));

        String orderPlacedDetailedExpectedText = verifyOrderPlacedDetailedText.getText();
        String orderPlacedDetailedActualText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(orderPlacedDetailedActualText, orderPlacedDetailedExpectedText, "Checkout Order Details are not correct!");
            System.out.println();
            System.out.println("Checkout details verified successfully!");


        } catch (AssertionError e) {
            System.out.println("Checkout Complete Page not opened. Assertion failed: " + e.getMessage());
            Assert.fail("Checkout complete text verification failed!");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Unexpected error while verifying checkout complete page!");
        }


    }




}
