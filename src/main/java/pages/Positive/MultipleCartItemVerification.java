package pages.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MultipleCartItemVerification {


    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public MultipleCartItemVerification(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By addtoCart1 = By.id("add-to-cart-sauce-labs-backpack");
    By removeBtn1 = By.id("remove-sauce-labs-backpack");
    By addtoCart2 = By.id("add-to-cart-sauce-labs-bike-light");
    By removeBtn2 = By.id("remove-sauce-labs-bike-light");
    By addtoCart3 = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By removeBtn3 = By.id("remove-sauce-labs-bike-light");
    By cartBtn = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By cartPageHeader = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By cartRemoveBtn1 = By.id("remove-sauce-labs-backpack");
    By cartRemoveBtn2 = By.id("remove-sauce-labs-bike-light");
    By cartRemoveBtn3 = By.id("remove-sauce-labs-bolt-t-shirt");
    By checkoutBtn = By.id("checkout");



    public void addItemsToCart(){
        //Add to cart first item
        WebElement addtoCart1Btn = wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCart1));
        addtoCart1Btn.click();

        //Verify remove btn visible
        String removeBtnActualText = "Remove";
        String removeBtnExpectedText = driver.findElement(removeBtn1).getText();

        WebElement removeBtn1Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn1));
        Assert.assertEquals(removeBtnActualText, removeBtnExpectedText, "Remove Button is not visible");
        System.out.println();
        System.out.println("Item 1's Remove Button is Visible");


        //Add to cart second item
        WebElement addToCartBtn2 = wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCart2));
        addToCartBtn2.click();

        //Verify remove btn visible
        String removeBtnActualText2 = "Remove";
        String removeBtnExpectedText2 = driver.findElement(removeBtn2).getText();

        WebElement removeBtn2Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn2));
        Assert.assertEquals(removeBtnActualText2, removeBtnExpectedText2, "Remove Button is not visible");
        System.out.println("Item 2's Remove Button is Visible");


        //Add to cart third item
        WebElement addToCartBtn3 = wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCart3));
        addToCartBtn3.click();

        //Verify remove btn visible
        String removeBtnActualText3 = "Remove";
        String removeBtnExpectedText3 = driver.findElement(removeBtn3).getText();

        WebElement removeBtn3Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn3));
        Assert.assertEquals(removeBtnActualText3, removeBtnExpectedText3, "Remove Button is not visible");
        System.out.println("Item 3's Remove Button is Visible");
    }

    public void goToCart(){
        WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBtn));
        cartButton.click();

        //Verify Cart Page
        String cartPageHeaderActualText = "Your Cart";
        String cartPageHeaderExpectedText = driver.findElement(cartPageHeader).getText();

        WebElement cartPageHeaderVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageHeader));
        Assert.assertEquals(cartPageHeaderActualText, cartPageHeaderExpectedText, "Cart Page is not visible");
        System.out.println();
        System.out.println("Cart Page is Visible");



        //Verify items in cart
        WebElement removeBtn1Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn1));
        Assert.assertTrue(removeBtn1Visible.isDisplayed(), "Item 1 is not in the cart");
        System.out.println();
        System.out.println("Item 1 is in the cart");

        WebElement removeBtn2Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn2));
        Assert.assertTrue(removeBtn2Visible.isDisplayed(), "Item 2 is not in the cart");
        System.out.println("Item 2 is in the cart");

        WebElement removeBtn3Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn3));
        Assert.assertTrue(removeBtn3Visible.isDisplayed(), "Item 3 is not in the cart");
        System.out.println("Item 3 is in the cart");

        List<WebElement> beforeRemove = driver.findElements(By.className("inventory_item_name"));
        System.out.println();
        System.out.println("Items before removing: " + beforeRemove.size());
        for (WebElement item : beforeRemove) {
            System.out.println(" - " + item.getText());
        }


        //Remove an item from cart
        WebElement removeBtn1Cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartRemoveBtn1));
        removeBtn1Cart.click();

        List<WebElement> afterRemove = driver.findElements(By.className("inventory_item_name"));
        System.out.println();
        System.out.println("Items after removing: " + afterRemove.size());

        //Verify item is removed from cart
        Assert.assertTrue(driver.findElements(cartRemoveBtn1).isEmpty(), "Item 1 is not removed from the cart");
        System.out.println();
        System.out.println("Item 1 is removed from the cart");

        // Assert item count reduced by 1
        Assert.assertEquals(afterRemove.size(), beforeRemove.size() - 1, "Item count did not decrease after removal");

        // Assert removed item not present
        boolean isBackpackStillPresent = afterRemove.stream().anyMatch(item -> item.getText().equals("Sauce Labs Backpack"));
        Assert.assertFalse(isBackpackStillPresent, "Removed item (Sauce Labs Backpack) still present in cart");
        System.out.println();
        System.out.println("Item successfully removed. Remaining items:");
        for (WebElement item : afterRemove) {
            System.out.println(" - " + item.getText());
        }

    }
}
