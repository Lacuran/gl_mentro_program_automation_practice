package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.stream.IntStream;

@Slf4j
public class AccountInformationPage {
    WebDriver driver;
    By[] editInfoID = {By.id("firstname"), By.id("middlename"), By.id("lastname"), By.id("current_password")};
    By saveButtonCssSelector = By.cssSelector("[title='Save']");

    public AccountInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public AccountInformationPage editUserInformation(String[] editInfoData) {
        log.info("Editing User Information");
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).clear());
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).sendKeys(editInfoData[i]));
        driver.findElement(editInfoID[3]).sendKeys(editInfoData[3]);
        return this;
    }

    @Step
    public MyAccountPage clickSaveButton() {
        log.info("Click Save Button");
        driver.findElement(saveButtonCssSelector).click();
        return new MyAccountPage(driver);
    }
}
