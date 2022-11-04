package live.panda.testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class VerifyTheItemsInMobileListTest {
    final static Logger LOGGER = Logger.getLogger(VerifyTheItemsInMobileListTest.class);

    @Test(description = "1")
    public void verifyItemInTheMobileListPageCanBeShortedByNameTest(){
        //variables
        final String URL = "http://live.techpanda.org/"; //names with final in CAPITAL LETTERS
        String expectedTitle = "Home page";
        By mobileXpath = By.xpath("//*[text()='Mobile']"); // no string but by element
        String mobileExpectedTitle = "Mobile";
        By selectByNameDropdownXpath = By.xpath("(//*[@title='Sort By'])[1]");

        //drive setup
        LOGGER.info("Initialization chromedriver");
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();

        LOGGER.info("1.Goto http://live.techpanda.org/");
        driver.get(URL); //correct version open urls

        LOGGER.info("2.Verify the title of the page");
        assertEquals(driver.getTitle(), expectedTitle);

        LOGGER.info("3.Click on Mobile menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("4.Verify the tittle of the page");
        assertEquals(driver.getTitle(), mobileExpectedTitle);

        LOGGER.info("5.In the list of all mobile, select SORT BY dropdown as name");
        Select selectByName = new Select(driver.findElement(selectByNameDropdownXpath));
        selectByName.selectByVisibleText("Name");

        LOGGER.info("6.Verify all product are sorted by name");

        List<String> phoneList = driver.findElements(By.xpath("//*[@class='product-image']")).stream()
                .map(ele -> ele.getAttribute("title")).toList();

        assertEquals(phoneList, phoneList.stream().sorted().toList());


        /*String elementTitle = driver.findElement(By.xpath(iPhoneXpath)).getAttribute("title");
        assertEquals(elementTitle, "IPhone");

        elementTitle = driver.findElement(By.xpath(samsungXpath)).getAttribute("title");
        assertEquals(elementTitle, "Samsung Galaxy");

        elementTitle = driver.findElement(By.xpath(xperiaXpath)).getAttribute("title");
        assertEquals(elementTitle, "Xperia");*/

        //cleaning after test
        driver.quit();
    }
}
