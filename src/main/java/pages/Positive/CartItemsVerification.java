package pages.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CartItemsVerification {

    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public CartItemsVerification(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By addtoCart1 = By.id("add-to-cart-sauce-labs-backpack");
    By removeBtn1 = By.id("remove-sauce-labs-backpack");
    By cartBtn = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By cartPageHeader = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By cartRemobeBtn = By.id("remove-sauce-labs-backpack");
    By checkoutBtn = By.id("checkout");



    public void addItemsToCart(){

        WebElement addtoCart1Btn = wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCart1));
        addtoCart1Btn.click();

        //Verify remove btn visible
        String removeBtnActualText = "Remove";
        String removeBtnExpectedText = driver.findElement(removeBtn1).getText();

        WebElement removeBtn1Visible = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn1));
        Assert.assertEquals(removeBtnActualText, removeBtnExpectedText, "Remove Button is not visible");
        System.out.println();
        System.out.println("Remove Button is Visible");
    }

    public void ClickonCart(){

        WebElement cartBtnVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBtn));
        cartBtnVisible.click();

        WebElement cartPageHeaderVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageHeader));
        Assert.assertTrue(cartPageHeaderVisible.isDisplayed(), "Your Cart Page is not visible");
        System.out.println();
        System.out.println("Your Cart Page is visible");
    }

    public void CartVerification() {

        WebElement cartRemoveBtnVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(cartRemobeBtn));
        Assert.assertTrue(cartRemoveBtnVisible.isDisplayed(), "Remove Button is not visible in Cart Page");
        System.out.println();
        System.out.println("Remove Button is visible in Cart Page");
        cartRemoveBtnVisible.click();

        WebElement checkoutBtnVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        Assert.assertTrue(checkoutBtnVisible.isDisplayed(), "Checkout Button is not visible in Cart Page");
        System.out.println();
        System.out.println("Checkout Button is visible in Cart Page");


        // Verify cart is empty
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 0, "Cart is not empty after removing the item");
        System.out.println();
        System.out.println("The Cart is empty after removing the item");

    }










}
