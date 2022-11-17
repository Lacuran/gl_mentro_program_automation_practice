package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerLoginPage {

    WebDriver driver;

    By createAccButton = By.cssSelector("[title='Create an Account']");
    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateNewCustomerPage clickCreateAccountPage(){
        driver.findElement(createAccButton).click();
        return new CreateNewCustomerPage(driver);
    }
}
