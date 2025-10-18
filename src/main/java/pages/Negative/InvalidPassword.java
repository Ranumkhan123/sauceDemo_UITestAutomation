package pages.Negative;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class InvalidPassword {

    public WebDriver driver;
    public WebDriverWait wait;


    // Constructor
    public InvalidPassword(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private final By userName = By.id("user-name");
    private final By standardPassword = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

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

        userField.sendKeys(username);

        passField.sendKeys(invalidPassword);

        loginBtn.click();

        // Verify login is successful by checking the text on the screen
        try {
            WebElement loginErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String actualErrorText1 = loginErrorMessage.getText();

            Assert.assertTrue(actualErrorText1.contains("Epic sadface"),
                    "Error message should contain 'Epic sadface'");
            System.out.println("Error Message Displayed: " + actualErrorText1);

        } catch (Exception e) {
            System.out.println("Error message not found!");
            Assert.fail("Login Failed: Anyother error message displayed.");
        }
    }


}
