package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyTheComparePopUpWindowTest extends BaseTestSetup {

    @Test
    public void VerifyThatYouAreAbleToCompareTwoProduct(){
        //variables

        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By addToCompareButton = By.cssSelector(".link-compare");
        By compareButton = By.cssSelector("[title='Compare']");

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


        LOGGER.info("6. Close the pop-up window");

    }
}
