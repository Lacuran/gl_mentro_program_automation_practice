package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import utility.PhoneTypes;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
        return By.cssSelector("[title='" + phoneName + "']");
    }

    public String getMobilePageTitle(){
        return driver.getTitle();
    }

    public void clickSelectByNameDropdown(){
        Select selectByName = new Select(driver.findElement(selectByNameDropdown));
        selectByName.selectByVisibleText("Name");
    }

    public List<String> getMobileList() {
        return driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();
    }

    public String getPhoneCostString(String phoneName) {
        return driver.findElement(RelativeLocator.with(phonePrice).below(phoneCssSelector(phoneName))).getText();
    }


    public PhoneTypes clickPhoneCssSelector(String phoneName) {
        driver.findElement(phoneCssSelector(phoneName)).click();
        return new PhoneTypes(driver);
    }

    public PhoneTypes getPhoneTypes(){
        return new PhoneTypes(driver);
    }

    public ShoppingCartPage addToCart(){
        driver.findElements(addToCartCss).stream().findAny().orElseThrow().click();
        return new ShoppingCartPage(driver);
    }

    private void addPhoneToCompare(String phoneName){
        driver.findElement(RelativeLocator.with(addToCompareButton)
                        .below(By.cssSelector("[title='" + phoneName + "']")))
                .click();

    }

    public MobilePage addToCompare(String[] phoneArray) {
        Arrays.stream(phoneArray).forEach(phoneName -> addPhoneToCompare(phoneName));
        return this;
    }

    public MobilePage clickOnCompareButton(){
        driver.findElement(compareButton).click();
        return this;
    }

    public ComparePage switchToPopUp(){
        this.popup = driver.getWindowHandles()
                .stream()
                .filter(currentWindow -> !currentWindow.equalsIgnoreCase(mainWindowHandle))
                .findFirst()
                .orElseThrow();
        driver.switchTo().window(popup);
        driver.manage().window().maximize();
        return new ComparePage(driver);
    }

    public MobilePage switchToMainWindow(){
        driver.switchTo().window(mainWindowHandle);
        return this;
    }

    public MobilePage assertMobilePageTitle(String expectedMobilePageTitle, String assertionErrorMessage){
        assertEquals(getMobilePageTitle(), expectedMobilePageTitle, assertionErrorMessage);
        return this;
    }

}
