package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class ComparePage {

    WebDriver driver;
    MobilePage mobilePage;
    By actualTitlePopupXpath = By.xpath("//*[@class='page-title title-buttons']/h1");
    By closePopupWindowButton = By.cssSelector("[title='Close Window']");
    SoftAssert softAssert = new SoftAssert();
    public ComparePage(WebDriver driver) {
        this.driver = driver;
    }

    public MobilePage switchToMainWindow(){
        mobilePage.switchToMainWindow();
        return new MobilePage(driver);
    }

    public ComparePage assertPopUpPageTitle(String expectedPopUpTitle, String assertionErrorMessage){
        softAssert.assertEquals(getPopUpPageTitle(), expectedPopUpTitle, assertionErrorMessage);
        return this;
    }

    public ComparePage assertPhonesOnPopUp(String[] expectedArrayPhoneList, String assertionErrorMessage){
        softAssert.assertEquals(getPhoneList(), Arrays.stream(expectedArrayPhoneList).map(ex -> ex.toUpperCase()).sorted().toList(), assertionErrorMessage);
        softAssert.assertAll();
        return this;
    }

    private List<String> getPhoneList() {
        List<WebElement> phoneNamesInThePopup = driver.findElements(By.cssSelector(".product-name"));
        List<String> phoneList = phoneNamesInThePopup.stream()
                .map(element -> element.getText())
                .sorted()
                .toList();
        return phoneList;
    }

    public String getPopUpPageTitle(){
        return driver.findElement(actualTitlePopupXpath).getText();
    }

    public ComparePage closePopUpWindow(){
        driver.findElement(closePopupWindowButton).click();
        return this;
    }
}
