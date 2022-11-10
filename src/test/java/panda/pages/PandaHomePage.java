package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PandaHomePage {
    WebDriver driver;
    By mobileLink = By.xpath("//*[text()='Mobile']");

    public PandaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMobileLink(){
        driver.findElement(mobileLink).click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
