package panda.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import utility.PhoneTypes;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class MobilePage {
    WebDriver driver;
    String mainWindowHandle;
    String popup;
    By selectByNameDropdown = By.xpath("(//*[@title='Sort By'])[1]");
    By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]");
    By addToCartCss = By.cssSelector(".btn-cart");
    By addToCompareButton = By.cssSelector(".link-compare");
    By compareButton = By.cssSelector("[title='Compare']");

    public MobilePage(WebDriver driver) {
        this.driver = driver;
        this.mainWindowHandle = driver.getWindowHandles()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    private By phoneCssSelector(String phoneName) {
        log.info("Getting Data on " + phoneName);
        return By.cssSelector("[title='" + phoneName + "']");
    }

    public String getMobilePageTitle() {
        log.info("Getting Mobile Page Title");
        return driver.getTitle();
    }

    public void clickSelectByNameDropdown() {
        log.info("Selecting by Name sorting");
        Select selectByName = new Select(driver.findElement(selectByNameDropdown));
        selectByName.selectByVisibleText("Name");
    }

    public List<String> getMobileList() {
        log.info("Getting Mobile list");
        return driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();
    }

    public String getPhoneCostString(String phoneName) {
        log.info("Getting " + phoneName + " price");
        return driver.findElement(RelativeLocator.with(phonePrice).below(phoneCssSelector(phoneName))).getText();
    }


    public PhoneTypes clickPhoneCssSelector(String phoneName) {
        log.info("Clicking " + phoneName + " Css Selector");
        driver.findElement(phoneCssSelector(phoneName)).click();
        return new PhoneTypes(driver);
    }

    public PhoneTypes getPhoneTypes() {
        log.info("Getting into PhoneTypes");
        return new PhoneTypes(driver);
    }

    public ShoppingCartPage addToCart() {
        log.info("Click Add to Cart button");
        driver.findElements(addToCartCss).stream().findAny().orElseThrow().click();
        return new ShoppingCartPage(driver);
    }

    private void addPhoneToCompare(String phoneName) {
        log.info("Adding " + phoneName + " to compare");
        driver.findElement(RelativeLocator.with(addToCompareButton)
                        .below(By.cssSelector("[title='" + phoneName + "']")))
                .click();

    }

    public MobilePage addToCompare(String[] phoneArray) {
        log.info("Click Add to compare button");
        Arrays.stream(phoneArray).forEach(phoneName -> addPhoneToCompare(phoneName));
        return this;
    }

    public MobilePage clickOnCompareButton() {
        log.info("Click Compare button");
        driver.findElement(compareButton).click();
        return this;
    }

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

    public MobilePage switchToMainWindow() {
        log.info("Switching to Main Window");
        driver.switchTo().window(mainWindowHandle);
        return this;
    }

    public MobilePage assertMobilePageTitle(String expectedMobilePageTitle, String assertionErrorMessage) {
        log.info("Assert Mobile Page");
        assertEquals(getMobilePageTitle(), expectedMobilePageTitle, assertionErrorMessage);
        return this;
    }
}
