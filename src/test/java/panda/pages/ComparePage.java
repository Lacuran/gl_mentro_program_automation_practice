package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ComparePage {

    WebDriver driver;
    By actualTitlePopupXpath = By.xpath("//*[@class='page-title title-buttons']/h1");
    By closePopupWindowButton = By.cssSelector("[title='Close Window']");
    SoftAssert softAssert = new SoftAssert();

    public ComparePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public ComparePage assertPopUpPageTitle(String expectedPopUpTitle, String assertionErrorMessage) {
        log.info("Assert PopUp Page Title");
        softAssert.assertEquals(getPopUpPageTitle(), expectedPopUpTitle, assertionErrorMessage);
        return this;
    }

    @Step
    public ComparePage assertPhonesOnPopUp(String[] expectedArrayPhoneList, String assertionErrorMessage) {
        log.info("Assert Phones on PopUp");
        softAssert.assertEquals(getPhoneList(), Arrays.stream(expectedArrayPhoneList).map(ex -> ex.toUpperCase()).sorted().toList(), assertionErrorMessage);
        softAssert.assertAll();
        return this;
    }

    @Step
    private List<String> getPhoneList() {
        List<WebElement> phoneNamesInThePopup = driver.findElements(By.cssSelector(".product-name"));
        List<String> phoneList = phoneNamesInThePopup.stream()
                .map(element -> element.getText())
                .sorted()
                .toList();
        return phoneList;
    }

    @Step
    public String getPopUpPageTitle() {
        log.info("Getting PopUp page title");
        return driver.findElement(actualTitlePopupXpath).getText();
    }

    @Step
    public ProductListingPage closePopUpWindow() {
        log.info("Closing PopUp Window");
        driver.findElement(closePopupWindowButton).click();
        return new ProductListingPage(driver);
    }
}
