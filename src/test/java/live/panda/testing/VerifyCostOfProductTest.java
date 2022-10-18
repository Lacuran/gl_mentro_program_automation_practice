package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class VerifyCostOfProductTest extends BaseTestSetup {

    @DataProvider (name = "smartphone")
    public Object[][] dpMethod(){
        return new Object[][] {
                {"Sony Xperia", 1},
                {"Samsung Galaxy", 3},
                {"IPhone", 2}
        };
    }

    @Test (dataProvider = "smartphone")
    public void verifyCostOfProductInListPageAndDetailsPageTest(String phoneName, int priceNumber){

        //variables
//        By listPagePrice = By.xpath("//*[@id='product-price-1']");
//        By phoneCssSelector = By.xpath("//*[@title='Sony Xperia']")
//        By detailsPriceInfoCss = By.xpath("//*[@class='price-info']");


        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By listPagePrice = By.cssSelector("#product-price-" + priceNumber); //css selector (to learn) # - is for the id
        By phoneCssSelector = By.cssSelector("[title='" + phoneName + "']"); //css selector - [tag name='looked tag'] - specific tag
        By detailsPriceInfoCss = By.cssSelector("#product-price-" + priceNumber); //css selector . - css class


        LOGGER.info("2. Click on the 'MOBILE' menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In the list of all mobile, read the cost of Sony XPeria mobile. Note this value.");
        String xperiaCost = driver.findElement(listPagePrice).getText();

        LOGGER.info("4. Click on the Sony XPeria mobile");
        driver.findElement(phoneCssSelector).click();

        LOGGER.info("5. Read the cost of Sony XPeria mobile in detail page");
        String xperiaCostInDetailPage = driver.findElement(detailsPriceInfoCss).getText();

        LOGGER.info("6. Compare value in Step 3 and Step 5");
        assertEquals(xperiaCost, xperiaCostInDetailPage,"Compare value in Step 3 and Step 5");
//        assertNotEquals(xperiaCost,xperiaCostInDetailPage,"This is equal");

    }

    @Test
    public void verifyItemInTheMobileListPageCanBeShortedByNameTest() {
        //variables
        String expectedTitle = "Home page";
        By mobileXpath = By.xpath("//*[text()='Mobile']"); // no string but by element
        String mobileExpectedTitle = "Mobile";
        By selectByNameDropdownXpath = By.xpath("(//*[@title='Sort By'])[1]");



        LOGGER.info("2.Verify the title of the page");
        assertEquals(driver.getTitle(), expectedTitle);

        LOGGER.info("3.Click on Mobile menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("4.Verify the tittle of the page");
        assertEquals(driver.getTitle(), mobileExpectedTitle);

        LOGGER.info("5.In the list of all mobile, select SORT BY dropdown as name");
        Select selectByName = new Select(driver.findElement(selectByNameDropdownXpath));
        selectByName.selectByVisibleText("Name");

        LOGGER.info("6.Verify all product are sorted by name");

        List<String> phoneList = driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();

        assertEquals(phoneList, phoneList.stream().sorted().toList());



    }

}
