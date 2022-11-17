package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import utility.User;

import java.util.stream.IntStream;

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

    public CreateNewCustomerPage fillUserInformation(User user){
        fillRegistrationData(user);
        return this;
    }

    public MyAccountPage clickRegisterButton(){
        driver.findElement(submitAccButton).click();
        return new MyAccountPage(driver);
    }

    private void fillRegistrationData(User user) {
        String[] registrationData = {user.getFirstName(), user.getMiddleName(), user.getLastName()
                , user.getEmail(), user.getPassword(), user.getConfirmation()};
        IntStream.range(0, registrationForm.length)
                .forEach(i -> driver.findElement(registrationForm[i]).sendKeys(registrationData[i]));
    }

}
