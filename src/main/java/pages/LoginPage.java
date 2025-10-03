package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    public void loginApplication(String username, String password){

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(standardPassword));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        userField.sendKeys(username);
        passField.sendKeys(password);
        loginBtn.click();

        // Verify login is successful by checking the text on the screen

        try {
            // Case 1: Check if Products page loaded
            WebElement productPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            String actualText = productPageText.getText();

            Assert.assertEquals(actualText, "Products", "Login verification failed!");
            System.out.println("Login Successful!"); // Optional console message

        } catch (Exception e) {
            // Case 2: If Products not found, check error message
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
                String actualError = errorMessage.getText();

                Assert.assertTrue(actualError.contains("Epic sadface"), "Unexpected error message!");
                System.out.println("Login Failed! Error: " + actualError);

            } catch (Exception ex) {
                Assert.fail("Neither Products page nor error message found. Something unexpected happened.");
            }
        }


    }
}
