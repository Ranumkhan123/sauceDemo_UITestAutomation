package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class addProductsToCartPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public addProductsToCartPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Locators

    By addProductOne = By.id("add-to-cart-sauce-labs-bike-light");
    By addProductTwo = By.id("add-to-cart-sauce-labs-backpack");
    By addProductThree = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By addProductFour = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");
    By removeBtnProductOne = By.id("remove-sauce-labs-backpack");
    By removeBtnProductTwo = By.id("remove-sauce-labs-bike-light");
    By removeBtnProductThree = By.id("remove-sauce-labs-bolt-t-shirt");
    By removeBtnProductFour = By.id("remove-test.allthethings()-t-shirt-(red)");
    By addtoCartBtn = By.className("shopping_cart_link");
    By yourCartPage = By.xpath("//*[@id=\"header_container\"]/div[2]/span");


    // Save expected names of the products
    String expectedProduct1 = "Sauce Labs Backpack";
    String expectedProduct2 = "Sauce Labs Bike Light";
    String expectedProduct3 = "Sauce Labs Bolt T-Shirt";
    String expectedProduct4 = "Test.allTheThings() T-Shirt (Red)";

    // list to hold expected products dynamically
    List<String> expectedProducts = new ArrayList<>();



    public void addProductsToCart(){
        //Verify products are visible
        WebElement productOne = wait.until(ExpectedConditions.visibilityOfElementLocated(addProductOne));
        WebElement productTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(addProductTwo));
        WebElement productThree = wait.until(ExpectedConditions.visibilityOfElementLocated(addProductThree));
        WebElement productFour = wait.until(ExpectedConditions.visibilityOfElementLocated(addProductFour));

        productOne.click();
        expectedProducts.add(expectedProduct1);

        productTwo.click();
        expectedProducts.add(expectedProduct2);

        productThree.click();
        expectedProducts.add(expectedProduct3);

        productFour.click();
        expectedProducts.add(expectedProduct4);


        //Verify items actually added by checking "Remove" button appears
        boolean allAdded =
                wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtnProductOne)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtnProductTwo)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtnProductThree)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtnProductFour)).isDisplayed();

        if(allAdded){
            System.out.println();
            System.out.println("All selected items added to cart successfully!");
        } else {
            System.out.println("Selected Items were not added to the cart.");
        }

    }

    public void goToCart(){

        //Verify Cart button is visible
        WebElement clickCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCartBtn));
        //click cart btn
        clickCartBtn.click();

        //Verify cart page is visible
        WebElement youCartPage = wait.until(ExpectedConditions.visibilityOfElementLocated(yourCartPage));

        String cartPageExpectedText = youCartPage.getText();
        String cartPageActualText = "Your Cart";
        System.out.println();

        try {
            // Assertion for success
            Assert.assertEquals(cartPageActualText, cartPageExpectedText, "Cart Page not opened");
            // This line will only run if assertion passes
            System.out.println("Cart Page is Visible");
            System.out.println("Expected Text: " + cartPageExpectedText + " is same as Actual Text: " + " " + cartPageActualText);

        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Cart Page not opened");
            Assert.fail("Unexpected error while verifying cart item.");
        }

        //  verify that the added products in the cart are correct

        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProducts = new ArrayList<>();

        for (WebElement item : cartItems) {
            actualProducts.add(item.getText());
        }

        // Pretty print
        System.out.println("\n========== CART VERIFICATION ==========");
        System.out.println("Expected Products:");
        expectedProducts.forEach(p -> System.out.println("   ➡ " + p));

        System.out.println("\n Actual Products in Cart:");
        actualProducts.forEach(p -> System.out.println("   ➡ " + p));

        if (actualProducts.containsAll(expectedProducts)) {
            System.out.println("\n RESULT: Cart items verified successfully!");
        } else {
            System.out.println("\n RESULT: Cart verification failed!");
            System.out.println(" Missing items: " +
                    expectedProducts.stream().filter(p -> !actualProducts.contains(p)).toList());
            System.out.println(" Extra items: " +
                    actualProducts.stream().filter(p -> !expectedProducts.contains(p)).toList());
        }
        System.out.println("=======================================\n");
    }










}
