package panda.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PandaHomePage {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[text()='Mobile']") WebElement mobileLink;

    public PandaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMobileLink(){
        mobileLink.click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
