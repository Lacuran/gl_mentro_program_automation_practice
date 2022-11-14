package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import utility.PhoneTypes;

import java.util.List;

public class MobilePage {
    WebDriver driver;
    By selectByNameDropdown = By.xpath("(//*[@title='Sort By'])[1]");
    By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]");
    By addToCartCss = By.cssSelector(".btn-cart");


    public MobilePage(WebDriver driver) {
        this.driver = driver;
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

    public ShoppingCartPage addToCart(){
        driver.findElements(addToCartCss).stream().findAny().orElseThrow().click();
        return new ShoppingCartPage(driver);
    }

}
