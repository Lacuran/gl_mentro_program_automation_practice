package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class VerifyTheComparePopUpWindowTest extends BaseTestSetup {
    By addToCompareButton;

    @Test
    public void VerifyThatYouAreAbleToCompareTwoProduct(){
        //variables
        String[] phoneArray = {"IPhone", "Sony Xperia", "Samsung Galaxy"};
        By mobileXpath = By.xpath("//*[text()='Mobile']");
        addToCompareButton = By.cssSelector(".link-compare");
        By compareButton = By.cssSelector("[title='Compare']");
        By closePopupWindowButton = By.cssSelector("[title='Close Window']");
        By actualTitlePopupXpath = By.xpath("//*[@class='page-title title-buttons']/h1");
        String expectedPopupTitle = "COMPARE PRODUCTS";

//        By samsungCss = By.cssSelector("[title='Samsung Galaxy']");
//        By xperiaCss = By.cssSelector("[title='Sony Xperia']");

        LOGGER.info("2. Click on the 'MOBILE' menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In mobile product list, click on the 'Add to Compare' for 2 mobiles");
        Arrays.stream(phoneArray).forEach(phoneName -> addPhoneToCompare(phoneName));

        /*List<WebElement> addCompareButtons = driver.findElements(addToCompareButton);
        addCompareButtons.get(0).click();
        List<WebElement> addCompareButtons2 = driver.findElements(addToCompareButton);
        addCompareButtons2.get(1).click();*/

        LOGGER.info("4. Click on the 'COMPARE' button");
        driver.findElement(compareButton).click();


        LOGGER.info("5. Verify the pop-up window and check that the products are reflected in it");
        String mainWindowHandle = driver.getWindowHandle();
        String popup = driver.getWindowHandles()
                .stream()
                .filter(currentWindow -> !currentWindow.equalsIgnoreCase(mainWindowHandle))
                .findFirst()
                .orElseThrow();
        driver.switchTo().window(popup);
        driver.manage().window().maximize();

        List<WebElement> phoneNamesInThePopup = driver.findElements(By.className("product-name"));
        List<String> phoneList = phoneNamesInThePopup.stream()
                .map(element -> element.getText())
                .sorted()
                .toList();

        /*List<WebElement> samsung = driver.findElements(samsungCss);
        List<WebElement> sony = driver.findElements(xperiaCss);*/

        SoftAssert fewAsserts = new SoftAssert();
        fewAsserts.assertEquals(driver.findElement(actualTitlePopupXpath).getText(), expectedPopupTitle, "Check title");
        fewAsserts.assertEquals(phoneList, Arrays.stream(phoneArray).map(ex -> ex.toUpperCase()).sorted().toList(),"Check sony");


        LOGGER.info("6. Close the pop-up window");
        driver.findElement(closePopupWindowButton).click();
        driver.switchTo().window(mainWindowHandle);
        fewAsserts.assertEquals(driver.getTitle(), "Mobile", "Check mobile title page");
        fewAsserts.assertAll();

    }

    private void addPhoneToCompare(String phoneName){
        driver.findElement(RelativeLocator.with(addToCompareButton)
                .below(By.cssSelector("[title='" + phoneName + "']")))
                .click();
    }
}
