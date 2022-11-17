package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PandaHomePage {
    WebDriver driver;
    By mobileLink = By.xpath("//*[text()='Mobile']");

    public PandaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public MobilePage clickMobileLink(){
        driver.findElement(mobileLink).click();
        return new MobilePage(driver);
    }

    public String getHomePageTitle(){
        return driver.getTitle();
    }
}
