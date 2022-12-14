package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import utility.User;

import java.util.stream.IntStream;

@Slf4j
public class CreateNewCustomerPage {

    WebDriver driver;
    By[] registrationForm = {By.id("firstname"), By.id("middlename")
            , By.id("lastname"), By.id("email_address")
            , By.id("password"), By.id("confirmation")};
    By submitAccButton = RelativeLocator
            .with(By.cssSelector("[title='Register']"))
            .below(By.cssSelector(".button"));

    public CreateNewCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Filling User information")
    public CreateNewCustomerPage fillUserInformation(User user) {
        log.info("Filling User information");
        fillRegistrationData(user);
        return this;
    }

    @Step("Click register button")
    public MyAccountPage clickRegisterButton() {
        log.info("Click register button");
        driver.findElement(submitAccButton).click();
        return new MyAccountPage(driver);
    }

    @Step("Filling form")
    private void fillRegistrationData(User user) {
        String[] registrationData = {user.getFirstName(), user.getMiddleName(), user.getLastName()
                , user.getEmail(), user.getPassword(), user.getConfirmation()};
        IntStream.range(0, registrationForm.length)
                .forEach(i -> driver.findElement(registrationForm[i]).sendKeys(registrationData[i]));
    }

}
