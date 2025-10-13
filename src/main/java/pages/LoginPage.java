package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ScreenshotUtils;

import static utils.ExtentTestNGListener.testThread;
import java.time.Duration;



public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private final By userName = By.id("user-name");
    private final By standardPassword = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By productPage = By.xpath("//span[@class='title']");

    // Actions
    public LoginPage openBrowser(String url){
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
        System.out.println();
        return this;
    }

    public void loginApplication(String username, String password) {

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(standardPassword));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        testThread.get().info("Entering username: " + username);
        userField.sendKeys(username);

        testThread.get().info("Entering password: " + password);
        passField.sendKeys(password);

        testThread.get().info("Clicking login button");
        loginBtn.click();


        // Verify login is successful by checking the text on the screen

        String actualError = null;
        try {
            // Case 1: Check if Products page loaded
            WebElement productPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            String actualText = productPageText.getText();

            Assert.assertEquals(actualText, "Products", "Login verification failed!");
            System.out.println("Login Successful!"); // Optional console message

        } catch (Exception e) {
            // If Products page not found, mark test FAIL with error message
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
            actualError = errorMessage.getText();
            Assert.fail("Login Failed! Error: " + actualError); // <- Important
        }

        testThread.get().fail("Login Failed! Error: " + actualError);
        // Screenshot on failure
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "Login_Failure");
        if (screenshotPath != null) {
            testThread.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
        }

        Assert.fail("Login Failed! Error: " + actualError);
    }
}
