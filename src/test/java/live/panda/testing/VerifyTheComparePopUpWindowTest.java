package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class VerifyTheComparePopUpWindowTest extends BaseTestSetup {

    @Test
    public void VerifyThatYouAreAbleToCompareTwoProduct(){
        //variables

        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By addToCompareButton = By.cssSelector(".link-compare");
        By compareButton = By.cssSelector("[title='Compare']");
        By closePopupWindowButton = By.cssSelector("[title='Close Window']");
        By actualTitlePopupXpath = By.xpath("//*[@class='page-title title-buttons']/h1");
        By samsungCss = By.cssSelector("[title='Samsung Galaxy']");
        By xperiaCss = By.cssSelector("[title='Sony Xperia']");

        LOGGER.info("2. Click on the 'MOBILE' menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In mobile product list, click on the 'Add to Compare' for 2 mobiles");
        List<WebElement> addCompareButtons = driver.findElements(addToCompareButton);
        addCompareButtons.get(0).click();
        List<WebElement> addCompareButtons2 = driver.findElements(addToCompareButton);
        addCompareButtons2.get(1).click();

        LOGGER.info("4. Click on the 'COMPARE' button");
        driver.findElement(compareButton).click();


        LOGGER.info("5. Verify the pop-up window and check that the products are reflected in it");
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String ChildWindow : allWindowHandles) {
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }
        driver.manage().window().maximize();
        List<WebElement> samsung = driver.findElements(samsungCss);
        List<WebElement> sony = driver.findElements(xperiaCss);

        assertEquals(driver.findElement(actualTitlePopupXpath).getText(), "COMPARE PRODUCTS", "Check title");
        assertEquals(samsung.get(1).getText(), "SAMSUNG GALAXY","Check samsung");
        assertEquals(sony.get(1).getText(),"SONY XPERIA", "Check sony");

        LOGGER.info("6. Close the pop-up window");
        driver.findElement(closePopupWindowButton).click();
        driver.switchTo().window(mainWindowHandle);
        assertEquals(driver.getTitle(), "Mobile", "Check mobile title page");

    }
}
