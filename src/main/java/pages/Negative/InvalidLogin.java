package pages.Negative;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ScreenshotUtils;

import java.time.Duration;

import static utils.ExtentTestNGListener.testThread;

public class InvalidLogin {

    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public InvalidLogin(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By userName = By.id("user-name");
    By standardPassword = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");

    //Open Browser and URL
    public void openBrowser(String url){
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
        System.out.println();

    }


    public void loginApplication(String username, String invalidPassword) {


            WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(standardPassword));
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

            testThread.get().info("Entering username: " + username);
            userField.sendKeys(username);

            testThread.get().info("Entering password: " + invalidPassword);
            passField.sendKeys(invalidPassword);

            testThread.get().info("Clicking login button");
            loginBtn.click();

            // Verify login is successful by checking the text on the screen
            try {
                WebElement loginErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
                String actualErrorText1 = loginErrorMessage.getText();

                Assert.fail("Login Failed: " + actualErrorText1);
                testThread.get().fail("Login Failed: " + actualErrorText1);

            } catch (Exception e) {
                System.out.println("Error message not found!");
                Assert.fail("Login Failed: No error message displayed.");
                testThread.get().fail("Login Failed: No error message displayed.");
            }

            // Screenshot on failure
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "Login_Failure");
            if (screenshotPath != null) {
                testThread.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
            }
        }

}
