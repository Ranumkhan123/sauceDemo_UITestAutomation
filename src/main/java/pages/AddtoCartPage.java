package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddtoCartPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public AddtoCartPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By productHeader = By.id("back-to-products");
    By productItem = By.xpath("//*[@id=\"item_4_title_link\"]");
    By addToCartButton = By.id("add-to-cart");
    By removeButton = By.id("remove");
    By cartIcon = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By cartPage = By.className("cart_item_label");




    public void productAddtoCart() {

        WebElement clickProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(productItem));
        clickProduct.click();

        WebElement verifyHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(productHeader));
        System.out.println("Product Header is visible");
        System.out.println();

        WebElement addtocartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        addtocartBtn.click();

        WebElement removeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));


        WebElement gotoCart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        gotoCart.click();

        WebElement yourCartPage = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPage));

        WebElement cartpageTextValue = driver.findElement(By.className("inventory_item_name"));


        // Check item name in cart
        String actualText = cartpageTextValue.getText();
        String expectedText = "Sauce Labs Backpack";

        try {
            // Assertion for success
            Assert.assertEquals(actualText, expectedText, "Item was not added to the cart!");
            // This line will only run if assertion passes
            System.out.println("Item added to the cart successfully!");

        } catch (AssertionError e) {
            // Handle failed assertion
            System.out.println("Failed to add item to cart: " + e.getMessage());
            Assert.fail("Item was not added to cart!");

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Unexpected error occurred: " + e.getMessage());
            Assert.fail("Unexpected error while verifying cart item.");
        }

    }
}
