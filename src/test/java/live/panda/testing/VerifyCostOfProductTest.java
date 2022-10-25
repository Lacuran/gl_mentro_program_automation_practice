package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VerifyCostOfProductTest extends BaseTestSetup {

    @DataProvider (name = "smartphone")
    public Object[][] dpMethod(){
        return new Object[][] {
                {"Sony Xperia"},
                {"IPhone"},
                {"Samsung Galaxy"}
        };
    }

    @Test (dataProvider = "smartphone")
    public void verifyCostOfProductInListPageAndDetailsPageTest(String phoneName){

        //variables
//        By phonePrice = By.xpath("//*[@id='product-price-1']");
//        By phoneCssSelector = By.xpath("//*[@title='Sony Xperia']")
//        By detailsPriceInfoCss = By.xpath("//*[@class='price-info']");


        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By phonePrice = By.xpath("//span[contains(@id, 'product-price-')]"); //css selector (to learn) # - is for the id
        By phoneCssSelector = By.cssSelector("[title='" + phoneName + "']"); //css selector - [tag name='looked tag'] - specific tag


        LOGGER.info("2. Click on the 'MOBILE' menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In the list of all mobile, read the cost of Sony XPeria mobile. Note this value.");
        String phoneCost = driver.findElement(RelativeLocator.with(phonePrice).below(phoneCssSelector)).getText();

        LOGGER.info("4. Click on the Sony XPeria mobile");
        driver.findElement(phoneCssSelector).click();

        LOGGER.info("5. Read the cost of Sony XPeria mobile in detail page");
        String xperiaCostInDetailPage = driver.findElement(phonePrice).getText();

        LOGGER.info("6. Compare value in Step 3 and Step 5");
        assertEquals(phoneCost, xperiaCostInDetailPage,"Compare value in Step 3 and Step 5");
//        assertNotEquals(xperiaCost,xperiaCostInDetailPage,"This is equal");

    }

}
