package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class AdvanceSearchPage {
    WebDriver driver;
    By priceStartingRange = By.id("price");
    By priceEndRange = By.id("price_to");
    By searchButton = By.xpath("//*[@class='buttons-set']/button");
    By productNameXPath = By.xpath("//*[@class='product-image']");
    By priceResultXPath = By.xpath("//*[@class='price']");
    By advanceSearchLink = By.cssSelector("[title='Advanced Search']");

    public AdvanceSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Entering Prince Range")
    public AdvanceSearchPage enterPriceRange(String startPrice, String endPrice) {
        log.info("Entering Prince Range");
        driver.findElement(priceStartingRange).sendKeys(startPrice);
        driver.findElement(priceEndRange).sendKeys(endPrice);
        return this;
    }

    @Step("Click search button")
    public AdvanceSearchPage clickSearchButton() {
        log.info("Click search button");
        driver.findElement(searchButton).click();
        return this;
    }

    @Step("Click Advance Search Link")
    public AdvanceSearchPage clickAdvanceSearchLink() {
        log.info("Click Advance Search Link");
        driver.findElement(advanceSearchLink).click();
        return this;
    }

    @Step("Getting search result")
    public AdvanceSearchPage getSearchResult() {
        log.info("Getting search result");
        List<String> searchResult = driver.findElements(productNameXPath).stream()
                .map(ele -> ele.getAttribute("title"))
                .toList();
        List<String> priceResult = driver.findElements(priceResultXPath).stream()
                .map(WebElement::getText)
                .toList();
        log.info(String.valueOf(searchResult));
        log.info(String.valueOf(priceResult));
        return this;
    }
}
