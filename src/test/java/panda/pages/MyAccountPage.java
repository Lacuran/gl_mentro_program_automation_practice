package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class MyAccountPage {
    WebDriver driver;
    By registrationDoneText = By.cssSelector(".success-msg");
    By mobileLink = By.xpath("//*[text()='Mobile']");
    By tvXpath = By.xpath("//*[text()='TV']");


    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyAccountPage assertRegistration(String expectedRegistrationMsg, String assertionErrorMSG){
        assertEquals(driver.findElement(registrationDoneText).getText(), expectedRegistrationMsg
                , assertionErrorMSG);
        return this;
    }

    public MobilePage clickMobileLink(){
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }

    public TVPage clickTVLink() {
        driver.findElement(tvXpath).click();
        return new TVPage(driver);
    }
}
