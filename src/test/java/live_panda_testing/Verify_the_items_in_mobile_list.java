package live_panda_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Verify_the_items_in_mobile_list {

    @Test
    public void VerifyItemInTheMobileListPageCanBeShortedByNameTest(){
        //variables
        String url = "http://live.techpanda.org/";
        String expectedTitle = "Home page";
        String mobileXpath = "//*[@class='level0 ']";
        String mobileExpectedTitle = "Mobile";
        String selectByNameDropdownXpath = "//div[@class='category-products']/div[1]/div[1]/div[1]/select[1]";
        String iPhoneXpath = "//li[1]//a[@class='product-image']";
        String samsungXpath = "//li[2]//a[@class='product-image']";
        String xperiaXpath = "//li[3]//a[@class='product-image']";


        WebDriver driver = new ChromeDriver();

        //1.Goto http://live.techpanda.org/
        driver.navigate().to(url);

        //2.Verify the title of the page
        Assert.assertEquals(driver.getTitle(), expectedTitle);

        //3.Click on Mobile menu
        driver.findElement(By.xpath(mobileXpath)).click();

        //4.Verify the tittle of the page
        Assert.assertEquals(driver.getTitle(), mobileExpectedTitle);

        //5.In the list of all mobile, select SORT BY dropdown as name
        Select selectByName = new Select(driver.findElement(By.xpath(selectByNameDropdownXpath)));
        selectByName.selectByVisibleText("Name");

        //6.Verify all product are sorted by name
        String elementTitle = driver.findElement(By.xpath(iPhoneXpath)).getAttribute("title");
        Assert.assertEquals(elementTitle, "IPhone");

        elementTitle = driver.findElement(By.xpath(samsungXpath)).getAttribute("title");
        Assert.assertEquals(elementTitle, "Samsung Galaxy");

        elementTitle = driver.findElement(By.xpath(xperiaXpath)).getAttribute("title");
        Assert.assertEquals(elementTitle, "Xperia");

        //cleaning after test
        driver.quit();
    }
}
