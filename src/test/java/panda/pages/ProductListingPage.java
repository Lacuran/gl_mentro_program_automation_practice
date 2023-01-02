package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class ProductListingPage extends CommonPageElements {

    String mainWindowHandle;
    String popup;
    By selectByNameDropdown = By.xpath("(//*[@title='Sort By'])[1]");
    By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]");
    By addToCartCss = By.cssSelector(".btn-cart");
    By addToCompareButton = By.cssSelector(".link-compare");
    By compareButton = By.cssSelector("[title='Compare']");

    public ProductListingPage(WebDriver driver) {
        super(driver);
        this.mainWindowHandle = driver.getWindowHandles()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Step
    private By phoneCssSelector(String phoneName) {
        log.info("Getting Data on " + phoneName);
        return By.cssSelector("[title='" + phoneName + "']");
    }

    @Step
    public void clickSelectByNameDropdown() {
        log.info("Selecting by Name sorting");
        Select selectByName = new Select(driver.findElement(selectByNameDropdown));
        selectByName.selectByVisibleText("Name");
    }

    @Step
    public List<String> getMobileList() {
        log.info("Getting Mobile list");
        return driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();
    }

    @Step
    public String getPhoneCostString(String phoneName) {
        log.info("Getting " + phoneName + " price");
        return driver.findElement(RelativeLocator.with(phonePrice).below(phoneCssSelector(phoneName))).getText();
    }

    @Step
    public ProductDetailPage clickPhoneCssSelector(String phoneName) {
        log.info("Clicking " + phoneName + " Css Selector");
        driver.findElement(phoneCssSelector(phoneName)).click();
        return new ProductDetailPage(driver);
    }

    @Step
    public ShoppingCartPage addToCart() {
        log.info("Click Add to Cart button");
        driver.findElements(addToCartCss).stream().findAny().orElseThrow().click();
        return new ShoppingCartPage(driver);
    }

    @Step
    private void addPhoneToCompare(String phoneName) {
        log.info("Adding " + phoneName + " to compare");
        driver.findElement(RelativeLocator.with(addToCompareButton)
                        .below(By.cssSelector("[title='" + phoneName + "']")))
                .click();

    }

    @Step
    public ProductListingPage addToCompare(String[] phoneArray) {
        log.info("Click Add to compare button");
        Arrays.stream(phoneArray).forEach(phoneName -> addPhoneToCompare(phoneName));
        return this;
    }

    @Step
    public ProductListingPage clickOnCompareButton() {
        log.info("Click Compare button");
        driver.findElement(compareButton).click();
        return this;
    }

    @Step
    public ComparePage switchToPopUp() {
        log.info("Switching windows");
        this.popup = driver.getWindowHandles()
                .stream()
                .filter(currentWindow -> !currentWindow.equalsIgnoreCase(mainWindowHandle))
                .findFirst()
                .orElseThrow();
        driver.switchTo().window(popup);
        driver.manage().window().maximize();
        return new ComparePage(driver);
    }

    @Step
    public ProductListingPage switchToMainWindow() {
        log.info("Switching to Main Window");
        driver.switchTo().window(mainWindowHandle);
        return this;
    }

    @Step
    public ProductListingPage assertMobilePageTitle(String expectedMobilePageTitle, String assertionErrorMessage) {
        log.info("Assert Mobile Page");
        assertEquals(getPageTitle(), expectedMobilePageTitle, assertionErrorMessage);
        return this;
    }
}
