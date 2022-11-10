package panda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MobilePage {
    WebDriver driver;
    By selectByNameDropdown = By.xpath("(//*[@title='Sort By'])[1]");

    public MobilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void clickSelectByNameDropdown(){
        Select selectByName = new Select(driver.findElement(selectByNameDropdown));
        selectByName.selectByVisibleText("Name");
    }

    public List<String> getMobileList() {
        return driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();
    }
}
