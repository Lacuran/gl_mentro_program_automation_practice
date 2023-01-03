package panda.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import utility.User;

import java.util.stream.IntStream;

@Slf4j
public class CustomerLoginPage {

    WebDriver driver;
    By createAccButton = By.cssSelector("[title='Create an Account']");
    By[] loginID = {By.id("email"), By.id("pass")};
    By loginButton = By.id("send2");

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Create Account button")
    public CreateNewCustomerPage clickCreateAccountPage() {
        log.info("Click Create Account button");
        driver.findElement(createAccButton).click();
        return new CreateNewCustomerPage(driver);
    }

    @Step("Filling Data login")
    public CustomerLoginPage fillingLoginData(User user) {
        log.info("Filling Data login");
        String[] loginData = {user.getEmail(), user.getPassword()};
        IntStream.range(0, loginData.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));
        return this;
    }

    @Step("Click login button")
    public MyAccountPage clickLoginButton() {
        log.info("Click login button");
        driver.findElement(loginButton).click();
        return new MyAccountPage(driver);
    }
}
