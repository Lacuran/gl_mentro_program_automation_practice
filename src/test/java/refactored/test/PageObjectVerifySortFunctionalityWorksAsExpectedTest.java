package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
@Slf4j
public class PageObjectVerifySortFunctionalityWorksAsExpectedTest extends BaseTestSetup {

    @Test(description = "additional test '4'")
    public void sortFunctionalityWorksAsExpected(){
        //variables
        By advanceSearchLink = By.cssSelector("[title='Advanced Search']");
        By priceStartingRange = By.id("price");
        By priceEndRange = By.id("price_to");
        By searchButton = By.xpath("//*[@class='buttons-set']/button");

        log.info("2. Click on Advance Search");
        driver.findElement(advanceSearchLink).click();

        log.info("3. In Price field enter range 0-150. Click Search");
        driver.findElement(priceStartingRange).sendKeys("0");
        driver.findElement(priceEndRange).sendKeys("150");
        driver.findElement(searchButton).click();

        log.info("4. Note the Price and Product Name in the result. Print on console");
        List<String> searchResult = driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title"))
                .toList();
        List<String> priceResult = driver.findElements(By.xpath("//*[@class='price']")).stream()
                .map(WebElement::getText)
                .toList();
        log.info(String.valueOf(searchResult));
        log.info(String.valueOf(priceResult));

        log.info("5. Again, In Price field enter range 151-1000. Click Search");
        driver.findElement(advanceSearchLink).click();
        driver.findElement(priceStartingRange).sendKeys("151");
        driver.findElement(priceEndRange).sendKeys("1000");
        driver.findElement(searchButton).click();

        log.info("6. Note the Price and Product Name in the result. Print on console");
        List<String> searchResult2 = driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title"))
                .toList();
        List<String> priceResult2 = driver.findElements(By.xpath("//*[@class='price']")).stream()
                .map(WebElement::getText)
                .toList();

        log.info(String.valueOf(searchResult2));
        log.info(String.valueOf(priceResult2));
    }
}
