package pages.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import static utils.HelperUtils.shorten;




public class productVerification {

    public WebDriver driver;
    public WebDriverWait wait;


    // Constructor
    public productVerification(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By product1Name = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    By product1Des = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div");
    By product1Price = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div");
    By product1Image = By.xpath("//*[@id=\"item_4_img_link\"]/img");


    public void verifyProducts(){

        // Verification of Product 1
        String expectedProduct1Name = "Sauce Labs Backpack";
        String expectedProduct1Description = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String expectedProduct1Price = "$29.99";
        String expectedProduct1ImageAltText = "Sauce Labs Backpack";

        // Get actual values from the web elements
        String actualProduct1Name = driver.findElement(product1Name).getText();
        String actualProduct1Description = driver.findElement(product1Des).getText();
        String actualProduct1Price = driver.findElement(product1Price).getText();
        String actualProduct1ImageAltText = driver.findElement(product1Image).getAttribute("alt");

        System.out.println();
        System.out.println("--- Verifying Products Details ---");
        //Product Name Assertions
        Assert.assertEquals(actualProduct1Name, "Sauce Labs Backpack", "Same Product Name");
        System.out.println("Product Name: " +  expectedProduct1Name + " is same as " + actualProduct1Name);

        //Product Description Assertions
        Assert.assertEquals(actualProduct1Description, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "Same Description");
        System.out.println("Product Description : " +  shorten(expectedProduct1Description, 10) + " is same as " + shorten(actualProduct1Description, 10));

        //Product Price Assertions
        Assert.assertEquals(actualProduct1Price, "$29.99", "Same Price");
        System.out.println("Product Price: " +  expectedProduct1Price + " is same as " + actualProduct1Price);

        //Product Image Assertions
        WebElement productImage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(product1Image));
        Assert.assertTrue(productImage1.isDisplayed(), "Image is visible");
        System.out.println("Product Image: " +  expectedProduct1ImageAltText + " is same as " + actualProduct1ImageAltText);
        System.out.println();

    }









}
