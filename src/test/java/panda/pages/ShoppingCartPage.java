package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;

@Slf4j
public class ShoppingCartPage {

    WebDriver driver;
    By quantityInput = By.cssSelector("[title='Qty']");
    By updateButton = By.cssSelector("[title='Update']");
    By errorMsgPopUp = By.className("error-msg");
    By emptyCartButton = By.id("empty_cart_button");
    By emptyCartMsg = By.className("page-title");
    By selectorForCountry = By.id("country");
    By regionCart = By.id("region");
    By postcodeCart = By.id("postcode");
    By estimateButton = By.cssSelector("[title='Estimate']");
    By shippingPrice = RelativeLocator
            .with(By.cssSelector(".price"))
            .below(By.xpath("//dt[contains(text(), 'Flat Rate')]"));
    By radioButton = By.id("s_method_flatrate_flatrate");
    By updateTotalButton = By.cssSelector("[title='Update Total']");
    By totalPriceXPath = By.xpath("//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span");
    By checkoutButton = RelativeLocator
            .with(By.cssSelector(".btn-checkout"))
            .below(By.cssSelector(".top"));

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShoppingCartPage changeQtyAndClickUpdate(String Quantity) {
        log.info("Changing Quantity to " + Quantity);
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(Quantity);
        driver.findElement(updateButton).click();
        return this;
    }

    public ShoppingCartPage emptyShoppingCart() {
        log.info("Emptying shopping cart");
        driver.findElement(emptyCartButton).click();
        return this;
    }

    public ShoppingCartPage fillShippingAndTaxField(String country, String region, String zipCode) {
        log.info("Filling Shipping and Tax field");
        Select countrySelect = new Select(driver.findElement(selectorForCountry));
        countrySelect.selectByValue(country);
        driver.findElement(regionCart).sendKeys(region);
        driver.findElement(postcodeCart).sendKeys(zipCode);
        return this;
    }

    public ShoppingCartPage clickEstimateLink() {
        log.info("Click Estimate link");
        driver.findElement(estimateButton).click();
        return this;
    }

    public ShoppingCartPage selectRadioButton() {
        log.info("Selecting price on Radio buttons");
        driver.findElement(radioButton).click();
        return this;
    }

    public ShoppingCartPage clickUpdateTotalLink() {
        log.info("Click Update total");
        driver.findElement(updateTotalButton).click();
        return this;
    }

    public CheckoutPage clickCheckoutButton() {
        log.info("Click Checkout button");
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }

    public ShoppingCartPage verifyTheCartIsEmpty(String expectedMSG, String assertionErrorMessage) {
        log.info("Assert Cart is Empty");
        assertEquals(driver.findElement(emptyCartMsg).getText(), expectedMSG, assertionErrorMessage);
        return this;
    }

    public ShoppingCartPage assertErrorMSG(String expectedMSG, String assertionErrorMessage) {
        log.info("Assert Error MSG");
        assertEquals(driver.findElement(errorMsgPopUp).getText(), expectedMSG, assertionErrorMessage);
        return this;
    }

    public ShoppingCartPage assertShippingCost(String expectedCost, String assertionErrorMessage) {
        log.info("Assert Shipping Cost");
        assertEquals(driver.findElement(shippingPrice).getText(), expectedCost, assertionErrorMessage);
        return this;
    }

    public ShoppingCartPage assertTotalShippingCost(String expectedTotalPrice, String assertionErrorMessage) {
        log.info("Assert Total Shipping Cost");
        assertEquals(driver.findElement(totalPriceXPath).getText(), expectedTotalPrice, assertionErrorMessage);
        return this;
    }
}
