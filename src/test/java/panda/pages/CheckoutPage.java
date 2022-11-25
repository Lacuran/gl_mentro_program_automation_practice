package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.IntStream;
@Slf4j
public class CheckoutPage {

    WebDriver driver;
    By billingAddressSelector = By.id("billing-address-select");
    By countrySelectID = By.id("billing:country_id");
    By[] shippingID = {By.id("billing:firstname"), By.id("billing:middlename")
            , By.id("billing:lastname"), By.id("billing:street1")
            , By.id("billing:city"), By.id("billing:postcode")
            , By.id("billing:telephone")};
    By continueButtonBillingInformation = By.xpath("//*[@id='billing-buttons-container']/button");
    By continueButtonShippingMethod = By.xpath("//*[@id='shipping-method-buttons-container']/button");
    By continueButtonPaymentInformation = By.xpath("//*[@id='payment-buttons-container']/button");
    By checkMoneyRadioButton = By.id("p_method_checkmo");
    By placeOrderButton = By.cssSelector("[title='Place Order']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage selectNewBillingAddress(String newAddress){
        log.info("Selecting new Billing Address");
        Select newAddressSelect = new Select(driver.findElement(billingAddressSelector));
        newAddressSelect.selectByVisibleText(newAddress);
        return this;
    }

    public CheckoutPage fillNewBillingInformation(String[] shippingData, String shippingCountryShortcut){
        log.info("Filling new billing information");
        Select shippingCountry = new Select(driver.findElement(countrySelectID));
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).clear());
        IntStream.range(0, shippingData.length)
                .forEach(i -> driver.findElement(shippingID[i]).sendKeys(shippingData[i]));
        shippingCountry.selectByValue(shippingCountryShortcut);
        return this;
    }

    public CheckoutPage clickBillingInformationContinueButton(){
        log.info("Click continue button on billing information");
        driver.findElement(continueButtonBillingInformation).click();
        return this;
    }

    public CheckoutPage clickShippingInformationContinueButton(){
        log.info("Click shipping information continue button");
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(continueButtonShippingMethod));
        driver.findElement(continueButtonShippingMethod).click();
        return this;
    }

    public CheckoutPage selectCheckMoneyRadioButton(){
        log.info("Select Check/Money radio button");
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(checkMoneyRadioButton));
        driver.findElement(checkMoneyRadioButton).click();
        return this;
    }

    public CheckoutPage clickPaymentInformationContinueButton(){
        log.info("Click payment information continue button");
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(continueButtonPaymentInformation));
        driver.findElement(continueButtonPaymentInformation).click();
        return this;
    }

    public PlacedOrderPage clickPlaceOrderButton(){
        log.info("Click place order button");
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        driver.findElement(placeOrderButton).click();
        return new PlacedOrderPage(driver);
    }

    private WebDriverWait explicitWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}